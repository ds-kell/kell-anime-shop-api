package com.kell.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiscountDto {
    private Integer id;
    private String name;
    private float value;
    private Instant startDate;
    private Instant endDate;

}
