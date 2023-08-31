package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
     
	  public List<Customer> findDistinctByEmail(String email);
	
}
