package com.example.ecomertest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userid")
    private User user;
    @OneToMany(mappedBy = "cart")
    private Set<CartItem> cartItems;
    @Column(columnDefinition = "boolean default true")
    private Boolean state=true;
    @OneToOne(mappedBy = "cart")
    private Order order;
}
