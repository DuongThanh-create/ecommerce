package com.example.ecomertest.repository;

import com.example.ecomertest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByCategoryProduct_Id(Long id);
}
