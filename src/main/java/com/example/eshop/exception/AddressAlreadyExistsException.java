package com.example.eshop.exception;

public class AddressAlreadyExistsException extends RuntimeException{
    public AddressAlreadyExistsException(String msg){
        super(msg);
    }
}
