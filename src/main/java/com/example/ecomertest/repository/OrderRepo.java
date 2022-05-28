package com.example.ecomertest.repository;

import com.example.ecomertest.entity.Cart;
import com.example.ecomertest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    Order findByCart(Cart cart);
    List<Order> findOrderByCart_User_Id(Long id);
}
