package com.example.eshop.model.dto;

import com.example.eshop.model.Customer;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDto extends BaseEntityDto{
    private String employeeId;

    private String employeeName;
    private String employeeSurname;
    private Integer point;
    private String email;
    private List<Customer> customers;
}
