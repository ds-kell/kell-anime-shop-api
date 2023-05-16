package com.kell.webapp.rest.user;


import com.kell.service.ProfileService;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ProfileResource {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(){
        return ResponseUtils.ok(profileService.getUserProfile());
    }
}
