package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Data
public class Employee extends BaseEntity{
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String employeeId;

    private String employeeName;
    private String employeeSurname;
    private Integer point;
    private String email;
    private String password;
    private List<Customer> customers;
}
