package com.spring.boot.rocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.entities.Customer;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long> {
}
