package com.ismailcet.ecommerceuserservice.repository;

import com.ismailcet.ecommerceuserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
