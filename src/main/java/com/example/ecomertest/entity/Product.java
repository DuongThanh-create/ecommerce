package com.example.ecomertest.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="shortdescription", columnDefinition = "TEXT")
    private String shortDescription;
    private Integer price;
    private Integer quantity;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryproduct")
    private CategoryProduct categoryProduct;
}
