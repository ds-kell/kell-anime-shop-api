package com.kell.webapp.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class ProfileRequest {
    private String fullName;
    private String phoneNumber;
    private Date dob;
    private String gender;
    private String avatarUrl;
}
