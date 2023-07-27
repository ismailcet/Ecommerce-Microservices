package com.ismailcet.ecommerceorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer userId;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "cargo_status")
    private CargoStatus cargoStatus;
    @Column(name = "address")
    private String address;
    @Column(name = "amount")
    private Double amount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
