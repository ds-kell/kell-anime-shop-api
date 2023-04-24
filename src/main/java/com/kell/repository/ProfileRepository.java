package com.kell.repository;

import com.kell.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByAccount_Username(String username);
    Optional<Profile> findByAccount_Email(String email);
}
