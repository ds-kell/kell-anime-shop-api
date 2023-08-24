package com.kell.webapp.rest.user;


import com.kell.service.ProfileService;
import com.kell.webapp.dto.request.ProfileRequest;
import com.kell.webapp.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin

public class ProfileResource {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        return ResponseUtils.ok("success", profileService.getUserProfile());
    }

    @PutMapping("profile/update")
    public ResponseEntity<?> updateUserProfile(@RequestBody ProfileRequest profileRequest) {
        profileService.updateProfile(profileRequest);
        return ResponseUtils.ok("Updated");
    }
}
