package com.spring.boot.rocks.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.boot.rocks.entities.Customer;
import com.spring.boot.rocks.repositories.CustomerJPARepository;

@RestController
@RequestMapping("/api/")
public class CustomerRESTController {

	@Autowired
	CustomerJPARepository customerRepo;

	@GetMapping("/getallcustomers")
	public List<Customer> getAllContacts() {
		return customerRepo.findAll();
	}

	@GetMapping("/customer/{id}")
	public Customer retrieveCustomer(@PathVariable long id) {
		Optional<Customer> customer = customerRepo.findById(id);
		if (!customer.isPresent())
			throw new CustomerNotFoundException("id-" + id);
		return customer.get();
	}

	@DeleteMapping("/customer/{id}")
	public void deleteCustomer(@PathVariable long id) {
		customerRepo.deleteById(id);
	}

	@PostMapping("/customer")
	public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
		Customer savedCustomer = customerRepo.save(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable long id) {
		Optional<Customer> customerOptional = customerRepo.findById(id);
		if (!customerOptional.isPresent())
			return ResponseEntity.notFound().build();
		customer.setId(id);
		customerRepo.save(customer);
		return ResponseEntity.noContent().build();
	}

}