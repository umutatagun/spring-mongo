package com.example.eshop.controller;

import com.example.eshop.model.dto.CustomerDto;
import com.example.eshop.exception.CustomerAlreadyExistsException;
import com.example.eshop.exception.CustomerNotFoundException;
import com.example.eshop.model.Customer;
import com.example.eshop.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    private ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity(customerService.getAllCustomers(), OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id) {
        return new ResponseEntity(customerService.getCustomerById(id),OK);
    }

    @PostMapping
    private ResponseEntity<CustomerDto> addCustomer(@RequestBody Customer c1) {
        return new ResponseEntity(customerService.addCustomer(c1),CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity(OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        return new ResponseEntity(customerService.updateCustomer(id,customer),CREATED);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    private ResponseEntity<String> customerAlreadyExistsException(CustomerAlreadyExistsException ex) {
        return new ResponseEntity(ex.getMessage(),BAD_REQUEST);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    private ResponseEntity<String> customerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(),NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<String> validationException(ConstraintViolationException ex) {
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<String> runtimeException(RuntimeException ex){
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }
}
