package com.kell.webapp.dto.response;

import lombok.Data;

@Data
public class AccountResponse {
    private Long id;
    private String username;
    private String email;
}
