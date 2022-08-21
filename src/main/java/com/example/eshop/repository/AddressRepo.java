package com.example.eshop.repository;

import com.example.eshop.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepo extends MongoRepository<Address,String> {
    List<Address> findByCustomerId(String customerId);
    void deleteAllByCustomerId(String customerId);
}
