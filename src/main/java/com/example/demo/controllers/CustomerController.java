package com.example.demo.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Customer;
import com.example.demo.repositories.CustomerRepository;

@RestController
@RequestMapping("api")
public class CustomerController {
	
	@Autowired
	private CustomerRepository repo;
	
	@PostMapping("/customer/create")
	public String create(@Valid @RequestBody Customer obj) {			
		repo.save(obj);
		
		return "OK";
	}

	@GetMapping("/customer/getAll")
	public Iterable<Customer> findAll() {			
		return repo.findAll();
	}
	
	@GetMapping("/customer/{id}")
	public Optional<Customer> findById(@PathVariable(value="id") Integer custId) {			
		return repo.findById(custId);
	}
	
	@PostMapping("/customer/delete/{id}")
	public Boolean delete(@PathVariable(value="id") Integer custId) {
		repo.deleteById(custId);
		return true;
	}
	@GetMapping("/customer/email/{email}")
	public Iterable<Customer> findByEmail(@PathVariable(value="email") String email) {			
		return repo.findByEmail2(email);
	}
	@GetMapping("/customer/address/{address}")
	public Iterable<Customer> findByAddress(@PathVariable(value="address") String address) {			
		return repo.findByAddress(address);
	}
	
	@GetMapping("/customer/all/{param}")
	public Iterable<Customer> findByAddressOrEmail(@PathVariable(value="param") String param) {			
		return repo.findByAddressOrEmail(param);
	}
	
	@PostMapping("/customer/all")
	public Iterable<Customer> findByAddressAndEmail(@Valid @RequestBody Map dataMap) {			
		return repo.findByAddressAndEmail((String)dataMap.get("email"),(String) dataMap.get("address"));
	}
}
