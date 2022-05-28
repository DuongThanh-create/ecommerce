package com.example.ecomertest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rolee")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "rolename")
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
