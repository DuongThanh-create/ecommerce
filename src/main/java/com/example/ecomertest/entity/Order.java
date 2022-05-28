package com.example.ecomertest.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="orderr")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="payment")
    private Payment payment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shipment")
    private Shipment shipment;
    @OneToOne
    @JoinColumn(name="cart")
    private Cart cart;
    @CreatedDate
    private Date orderdate;
}
