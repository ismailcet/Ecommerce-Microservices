package com.ismailcet.ecommerceorderservice.repository;

import com.ismailcet.ecommerceorderservice.dto.response.UserDto;
import com.ismailcet.ecommerceorderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findAllByUserId(Integer userId);
}
