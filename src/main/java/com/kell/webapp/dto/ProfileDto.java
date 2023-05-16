package com.kell.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProfileDto {
    private Integer id;
    private String fullName;
    private String phoneNumber;
    private Date dob;
    private String gender;
    private String avatarUrl;
    private AccountDto accountDto;
}
