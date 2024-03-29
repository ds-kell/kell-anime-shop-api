package com.kell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product_cart")
public class ProductCart {
    @EmbeddedId
    private ProductCartKey id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne
    @MapsId("productDetailId")
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
    @Column(name = "quantity")
    private int quantity;
}