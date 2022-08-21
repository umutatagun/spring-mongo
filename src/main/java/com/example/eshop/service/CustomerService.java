package com.example.eshop.service;

import com.example.eshop.model.Address;
import com.example.eshop.model.dto.CustomerDto;
import com.example.eshop.exception.CustomerAlreadyExistsException;
import com.example.eshop.exception.CustomerNotFoundException;
import com.example.eshop.model.Customer;
import com.example.eshop.repository.AccountRepo;
import com.example.eshop.repository.AddressRepo;
import com.example.eshop.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    private final AccountRepo accountRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10,new SecureRandom());

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public CustomerDto addCustomer(Customer c1) {
        if(customerRepo.findByEmail(c1.getEmail()).isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists!");
        }
        customerRepo.save(c1);

        if(!c1.getAddresses().isEmpty()){
            c1.getAddresses().forEach((address ->
                address.setCustomerId(c1.getCustomerId())
            ));
            addressRepo.saveAll(c1.getAddresses());
        }

        if(!c1.getAccounts().isEmpty()){
            c1.getAccounts().forEach(account ->
                account.setCustomerId(c1.getCustomerId())
            );
            accountRepo.saveAll(c1.getAccounts());

        }

        return customerToDto(c1);
    }

    public CustomerDto getCustomerById(String id) {
        Customer c1 = findCustomerById(id);
        return customerToDto(c1);
    }

    public void deleteCustomerById(String id) {
        Customer c1 = findCustomerById(id);
        addressRepo.deleteAllByCustomerId(id);
        customerRepo.deleteById(c1.getCustomerId());
    }

    public CustomerDto updateCustomer(String id,Customer c1) {
        Customer customer = findCustomerById(id);
        customer.setCustomerId(c1.getCustomerId());
        customer.setCustomerName(c1.getCustomerName());
        customer.setCustomerSurname(c1.getCustomerSurname());
        customer.setCustomerLevel(c1.getCustomerLevel());
        customer.setAge(c1.getAge());
        customer.setEmail(c1.getEmail());
        customer.setIsBlocked(c1.getIsBlocked());
        customer.setIsDeleted(c1.getIsDeleted());
        customer.setIsEmployee(c1.getIsEmployee());
        customer.setPassword(bCryptPasswordEncoder.encode(c1.getPassword()));
        customer.setResponseableEmployee(c1.getResponseableEmployee());
        customer.setLastModifiedBy(c1.getLastModifiedBy());
        customer.setLastModifiedDate(new Date());
        customer.setAddresses(c1.getAddresses());
        customer.setAccounts(c1.getAccounts());

        customerRepo.save(customer);

        return customerToDto(customer);
    }

    private CustomerDto customerToDto(Customer c1) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(c1.getCustomerId());
        customerDto.setCustomerName(c1.getCustomerName());
        customerDto.setCustomerLevel(c1.getCustomerLevel());
        customerDto.setCustomerSurname(c1.getCustomerSurname());
        customerDto.setEmail(c1.getEmail());
        customerDto.setAccounts(c1.getAccounts());
        customerDto.setAge(c1.getAge());
        customerDto.setIsBlocked(c1.getIsBlocked());
        customerDto.setIsDeleted(c1.getIsDeleted());
        customerDto.setIsEmployee(c1.getIsEmployee());
        customerDto.setResponseableEmployee(c1.getResponseableEmployee());
        customerDto.setLastModifiedDate(new Date());
        customerDto.setLastModifiedby(c1.getLastModifiedBy());
        customerDto.setAddresses(c1.getAddresses());

        return customerDto;
    }

    private Customer findCustomerById(String id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id "+id));
    }

}
