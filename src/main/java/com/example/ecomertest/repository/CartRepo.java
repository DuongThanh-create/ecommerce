package com.example.ecomertest.repository;

import com.example.ecomertest.entity.Cart;
import com.example.ecomertest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserAndState(User user, Boolean state);
}
