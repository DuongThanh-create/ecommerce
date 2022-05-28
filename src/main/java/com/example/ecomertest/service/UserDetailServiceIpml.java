package com.example.ecomertest.service;

import com.example.ecomertest.entity.User;
import com.example.ecomertest.entity.UserAcoount;
import com.example.ecomertest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceIpml implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByActiveAndUsername(true,username);
        return new UserAcoount(user);
    }
}
