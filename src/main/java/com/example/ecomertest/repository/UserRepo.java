package com.example.ecomertest.repository;

import com.example.ecomertest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByActiveAndUsername(Boolean active, String username);
    User findByUsername(String username);
}
