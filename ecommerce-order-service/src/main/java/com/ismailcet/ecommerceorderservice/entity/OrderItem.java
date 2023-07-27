package com.ismailcet.ecommerceorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "product_id",nullable = false, updatable = false)
    private Integer productId;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
}

