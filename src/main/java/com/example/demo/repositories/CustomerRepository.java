package com.example.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Integer> {
	
	@Query("Select c from Customer c where c.email like concat('%',:email,'%')")
	Iterable<Customer> findByEmail(@Param("email") String email);
	
	@Query("Select c.fullName, c.address, c.email from Customer c where c.email like concat('%',:email,'%')")
	Iterable<Customer> findByEmail2(@Param("email") String email);
	
	Iterable<Customer> findByAddress(@Param("address") String address);
		
	@Query("Select c from Customer c where c.email like concat('%',:email,'%') or c.address like concat('%',:email,'%')")
	Iterable<Customer> findByAddressOrEmail(@Param("email") String email);
	 
	@Query("Select c from Customer c where c.email like concat('%',:email,'%') and c.address like concat('%',:address,'%')")
	Iterable<Customer> findByAddressAndEmail(String email,String address);
}
