package com.example.ecomertest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Entity
@Table(name="userr")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "username khong duoc rong")
    private String username;
    @NotEmpty(message = "password khong duoc rong")
    private String fullname;
    @NotEmpty(message = "fullname khong duoc rong")
    private String password;
    @NotEmpty(message = "email khong duoc rong")
    @Email(message = "Email phai hop le")
    private String email;
    @Column(columnDefinition = "boolean default true")
    private Boolean active=true;
    private String address;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "userr_rolee",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid")
    )
    private Set<Role> roles;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Cart> carts;
}
