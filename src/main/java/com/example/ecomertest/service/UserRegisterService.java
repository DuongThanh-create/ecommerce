package com.example.ecomertest.service;

import com.example.ecomertest.entity.Role;
import com.example.ecomertest.entity.User;
import com.example.ecomertest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserRegisterService{
    @Autowired
    private UserRepo userRepo;


    public boolean register(User user){

        if(checkIfUserExist(user.getUsername())){
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRoleName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);
        return true;
    }

    public boolean checkIfUserExist(String username) {
        return userRepo.findByUsername(username) !=null ? true : false;
    }

}
