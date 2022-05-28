package com.example.ecomertest.repository;

import com.example.ecomertest.entity.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryProduct,Long> {
}
