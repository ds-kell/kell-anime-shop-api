package com.kell.webapp.dto;
import com.kell.model.ShopImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShopInformationDto {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    private String description;
    private List<ShopImage> shopImages;

}
