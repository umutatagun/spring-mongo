package com.example.eshop.controller;

import com.example.eshop.exception.AddressAlreadyExistsException;
import com.example.eshop.exception.AddressNotFoundException;
import com.example.eshop.model.Address;
import com.example.eshop.model.dto.AddressDto;
import com.example.eshop.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/addresses")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    private ResponseEntity<List<AddressDto>> getAllAddresses() {
        return new ResponseEntity(addressService.getAllAddresses(),OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<AddressDto> getAddressById(@PathVariable String id){
        return new ResponseEntity(addressService.getAddressById(id),OK);
    }

    @PostMapping
    private ResponseEntity<AddressDto> addAddress(@RequestBody Address address) {
        return new ResponseEntity(addressService.addAddress(address),CREATED);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteAddress(@PathVariable String id){
        addressService.deleteAddress(id);
        return new ResponseEntity(OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<AddressDto> updateAddress(@PathVariable String id, @RequestBody Address address) {
        return new ResponseEntity(addressService.updateAddress(id,address),CREATED);
    }

    @ExceptionHandler(AddressAlreadyExistsException.class)
    private ResponseEntity<String> addressAlreadyExistsException(AddressAlreadyExistsException ex) {
        return new ResponseEntity(ex.getMessage(),BAD_REQUEST);
    }

    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity<String> addressNotFoundException(AddressNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<String> validationException(ConstraintViolationException ex) {
        return new ResponseEntity(ex.getMessage(), BAD_REQUEST);
    }

}
