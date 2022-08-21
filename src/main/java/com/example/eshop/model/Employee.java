package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document
public class Employee extends BaseEntity{
    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String employeeId;

    private String employeeName;
    private String employeeSurname;
    private Integer point = 0;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    private List<Customer> customers = List.of();
}
