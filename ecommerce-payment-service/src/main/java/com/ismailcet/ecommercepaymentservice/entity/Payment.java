package com.ismailcet.ecommercepaymentservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "orderId")
    private Integer orderId;
    @Column(name = "address")
    private String address;
    @Column(name = "paymentInfo")
    private String paymentInfo;
}
