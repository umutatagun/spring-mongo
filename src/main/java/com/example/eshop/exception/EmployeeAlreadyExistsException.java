package com.example.eshop.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(String msg){
        super(msg);
    }
}
