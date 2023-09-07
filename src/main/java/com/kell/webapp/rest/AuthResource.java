package com.kell.webapp.rest;

import com.kell.service.AuthService;
import com.kell.webapp.dto.request.LoginRequest;
import com.kell.webapp.dto.request.SignupRequest;
import com.kell.webapp.dto.response.utils.Response;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> authenticateAccount(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseUtils.ok("success", authService.authenticateAccount(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> registerAccount(@Valid @RequestBody SignupRequest signupRequest) {
        authService.registerAccount(signupRequest);
        return ResponseUtils.created();
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<Response> refreshToken(@Valid @RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        final String space = "\\s+";
        return ResponseUtils.ok("verified", authService.verifyExpiration(refreshToken.split(space)[1]));
    }

}
