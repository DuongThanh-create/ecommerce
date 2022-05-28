package com.example.ecomertest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
public class UserGrandedAuthority implements GrantedAuthority {
    private Role role;
    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
