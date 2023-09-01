package com.kell.service;


import com.kell.webapp.dto.ProfileDto;
import com.kell.webapp.dto.request.ProfileRequest;

public interface ProfileService {
    ProfileDto getUserProfile();

    void updateProfile(ProfileRequest profileReq);
}
