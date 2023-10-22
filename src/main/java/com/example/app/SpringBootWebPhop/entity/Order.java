package com.example.app.SpringBootWebPhop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "order_code")
    String orderCode;
    @Column(name = "buyer_name")
    String userName;
    @Column(name = "buyer_phone")
    String userPhone;
    @Column(name = "buyer_email")
    String userEmail;
    @Column(name = "content")
    String content;
    @Column(name = "status")
    String status;

}