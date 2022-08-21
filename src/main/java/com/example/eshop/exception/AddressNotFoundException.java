package com.example.eshop.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String msg){
        super(msg);
    }
}
