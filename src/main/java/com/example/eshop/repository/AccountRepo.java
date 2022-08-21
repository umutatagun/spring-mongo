package com.example.eshop.repository;

import com.example.eshop.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends MongoRepository<Account, String> {
    List<Account> findAllByCustomerId(String customerId);
}
