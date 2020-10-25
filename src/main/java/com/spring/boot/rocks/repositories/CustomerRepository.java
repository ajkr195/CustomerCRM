package com.spring.boot.rocks.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.rocks.entities.Customer;

@Repository
public interface CustomerRepository
		extends PagingAndSortingRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {
}
