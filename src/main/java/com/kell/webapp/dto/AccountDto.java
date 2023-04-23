package com.kell.webapp.dto;

import com.kell.model.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountDto {
    private Integer id;
    private String username;
    private String email;
    private Set<Authority> authorities = new HashSet<Authority>();
}
