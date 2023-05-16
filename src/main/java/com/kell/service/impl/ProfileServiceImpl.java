package com.kell.service.impl;

import com.kell.repository.ProfileRepository;
import com.kell.service.ProfileService;
import com.kell.service.utils.MappingHelper;
import com.kell.webapp.dto.AccountDto;
import com.kell.webapp.dto.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final MappingHelper mappingHelper;

    @Override
    @Transactional
    public ProfileDto getUserProfile() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return profileRepository.findByAccount_Username(username)
                .map(profile -> {
                    var profileDto = mappingHelper.map(profile, ProfileDto.class);
                    profileDto.setAccountDto(mappingHelper.map(profile.getAccount(), AccountDto.class));
                    return profileDto;
                })
                .orElseThrow(() -> new RuntimeException("Not found profile with account: " + username));
    }

}
