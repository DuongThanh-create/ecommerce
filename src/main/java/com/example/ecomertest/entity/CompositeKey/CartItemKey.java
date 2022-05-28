package com.example.ecomertest.entity.CompositeKey;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class CartItemKey implements Serializable {
    private Long cartId;
    private Long productId;
}
