package com.kell.service.impl;

import com.kell.config.security.AuthoritiesConstants;
import com.kell.config.security.jwt.JwtUtils;
import com.kell.model.Account;
import com.kell.model.Authority;
import com.kell.model.Cart;
import com.kell.model.Profile;
import com.kell.repository.AccountRepository;
import com.kell.repository.AuthorityRepository;
import com.kell.repository.CartRepository;
import com.kell.repository.ProfileRepository;
import com.kell.service.AuthService;
import com.kell.webapp.dto.request.LoginRequest;
import com.kell.webapp.dto.request.SignupRequest;
import com.kell.webapp.dto.response.JwtResponse;
import com.kell.webapp.exception.EntityNotFoundException;
import com.kell.webapp.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public JwtResponse authenticateAccount(LoginRequest loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtAccessToken = jwtUtils.generateJwtAccessToken(authentication);
            String jwtRefreshToken = jwtUtils.generateRefreshToken(loginRequest.getUsername());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            List<String> authorities = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return new JwtResponse(jwtAccessToken, jwtRefreshToken, "Bearer", userDetails.getUsername(), authorities);

        } catch (AuthenticationException authenticationException) {
            throw new ServiceException("Username or password is invalid", "err.authorize.unauthorized");
        }

    }

    @Override
    @Transactional
    public void registerAccount(SignupRequest signupRequest) {
        if (accountRepository.existsByUsernameOrEmail(signupRequest.getUsername(), signupRequest.getEmail()))
            throw new ServiceException("Email or username is existed in system", "err.api.email-username-is-existed");

        Account account = new Account();
        account.setUsername(signupRequest.getUsername());
        account.setEmail(signupRequest.getEmail());
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> authoritiesString = signupRequest.getAuthorities();
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesString == null) {
            authorities.add(authorityRepository.findByName(AuthoritiesConstants.CUSTOMER).orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), AuthoritiesConstants.CUSTOMER)
            ));
        } else {
            authoritiesString.forEach(authority -> authorities.add(authorityRepository.findByName(authority).orElseThrow(
                    () -> new EntityNotFoundException(Authority.class.getName(), authority)
            )));
        }
        account.setAuthorities(authorities);
        accountRepository.save(account);

        profileRepository.save(Profile.builder().account(account).build());

        //Create cart if account have role customer
        if (authorities.stream().map(Authority::getName).collect(Collectors.toList()).contains(AuthoritiesConstants.CUSTOMER))
            cartRepository.save(Cart.builder()
                    .account(account).build());
    }

    @Override
    public JwtResponse verifyExpiration(String refreshToken) {
        String username = jwtUtils.validateRefreshToken(refreshToken);
        if (username == null || username.isEmpty()) {
            throw new ServiceException("Login session has expired", "err.token.expired");
        } else {
            return new JwtResponse(jwtUtils.generateJwtAccessToken(username), jwtUtils.generateRefreshToken(username), "Bearer", username, new ArrayList<>());
        }
    }
}
