package com.kell.service;


import com.kell.webapp.dto.ProfileDto;
import com.kell.webapp.dto.request.ProfileRequest;

import java.util.List;

public interface ProfileService {
    ProfileDto getUserProfile();

    void updateProfile(ProfileRequest profileReq);
}
