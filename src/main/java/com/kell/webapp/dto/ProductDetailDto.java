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
    private Date dateModified;
    private Size size;
    private float price;
    private int countInStock;
    private String imageUrl;
    private ProductDto productDto;
//    private String description;
}
