package com.example.ecomertest.repository;

import com.example.ecomertest.entity.Cart;
import com.example.ecomertest.entity.CartItem;
import com.example.ecomertest.entity.CompositeKey.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CartItemRepo extends JpaRepository<CartItem, CartItemKey> {
    CartItem findByCartItemKey(CartItemKey cartItemKey);
    Set<CartItem> findByCart(Cart cart);
}
