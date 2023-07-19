package com.ismailcet.ecommerceproductservice.repository;

import com.ismailcet.ecommerceproductservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
