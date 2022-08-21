package com.example.eshop.controller;

import com.example.eshop.exception.AccountAlreadyExistsException;
import com.example.eshop.exception.AccountNotFoundException;
import com.example.eshop.model.Account;
import com.example.eshop.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    private ResponseEntity<List<Account>> getAllAccounts(){
        return new ResponseEntity(accountService.getAllAccounts(),OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Account> getAccountById(@PathVariable String id) {
        return new ResponseEntity(accountService.getAccountById(id),OK);
    }

    @PostMapping
    private ResponseEntity<Account> addAccount(@RequestBody Account account){
        return new ResponseEntity(accountService.addAccount(account),CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Account> updateAccount(@PathVariable String id ,@RequestBody Account account) {
        return new ResponseEntity(accountService.updateAccount(id,account),CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteAccountById(@PathVariable String id){
        accountService.deleteAccountById(id);
        return new ResponseEntity(OK);
    }

    @DeleteMapping("/customer/{customerId}")
    private ResponseEntity<Void> deleteAccountByCustomerId(@PathVariable String customerId){
        accountService.deleteAccountsByCustomerId(customerId);
        return new ResponseEntity(OK);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    private ResponseEntity<String> accountAlreadExistsException(AccountAlreadyExistsException ex){
        return new ResponseEntity(ex.getMessage() ,BAD_REQUEST);
    }
    @ExceptionHandler(AccountNotFoundException.class)
    private ResponseEntity<String> accountNotFoundException(AccountNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<String> validationException(ConstraintViolationException ex) {
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }
}
