package com.prudentcpa.customerDB.repository;

import java.util.List;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.prudentcpa.customerDB.domain.Customer;

public interface CustomerRepository extends GemfireRepository<Customer, Long> {

	List<Customer> findByFirstName(String firstname);
	
	@Override
	public List<Customer> findAll();
}
