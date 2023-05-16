package com.kell.webapp.dto;

import com.kell.model.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDetailDto {
    private Integer id;
    private String type;
    private float price;
    private int count;
    private Date dateModified;
    private ProductDto productDto;
    private Size size;
    private String imageUrl;
//    private String description;
}
