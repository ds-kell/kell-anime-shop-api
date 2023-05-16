package com.kell.webapp.dto;

import com.kell.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String code;
    private String name;
    private Date createdAt;
    private Category category;
    private Material material;
    private Brand brand;
    private Discount discount;
    private List<ProductImage> images;
    private String description;
    @Override
    public String toString() {
        return this.name + " " + this.code + " " + this.brand;
    }
}