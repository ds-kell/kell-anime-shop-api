package com.kell.service.impl;

import com.kell.model.Account;
import com.kell.repository.AccountRepository;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Autowired
    public UserDetailServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (new EmailValidator().isValid(username, null)){
            return accountRepository
                    .findOneWithAuthoritiesByEmail(username)
                    .map(this::createUserSecurity)
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));
        }
        return accountRepository
                .findOneWithAuthoritiesByUsername(username)
                .map(this::createUserSecurity)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' is not exist in system"));

    }

    private org.springframework.security.core.userdetails.User createUserSecurity(Account account){
        Set<GrantedAuthority> securityAuthorities = account
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), securityAuthorities);

    }

}
