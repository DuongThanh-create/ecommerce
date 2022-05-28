package com.example.ecomertest.entity;

import com.example.ecomertest.entity.CompositeKey.CartItemKey;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="cartitem")
@Setter
@Getter
public class CartItem {
    @EmbeddedId
    private CartItemKey cartItemKey;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cart.class)
    @MapsId("cartId")
    @JoinColumn(name="cartid")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @MapsId("productId")
    @JoinColumn(name="productid")
    private Product product;
    private Integer quanity;
    @Transient
    private Integer totalprice;

}
