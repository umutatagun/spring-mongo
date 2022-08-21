package com.example.eshop.repository;

import com.example.eshop.model.Customer;
import com.example.eshop.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends MongoRepository<Customer,String> {
    Optional<Employee> findByEmail(String email);
    List<Customer> findAllByResponseableEmployee(String employeeId);
}
