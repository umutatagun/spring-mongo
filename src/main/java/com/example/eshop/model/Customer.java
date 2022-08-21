package com.example.eshop.model;

import com.example.eshop.model.enums.CustomerLevel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Data
public class Customer extends BaseEntity {

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String customerId;

    private String customerName;
    private String customerSurname;
    private Integer age;
    private CustomerLevel customerLevel;
    private String email;
    private List<Address> addresses;
    private List<Account> accounts;
    @Field(targetType = FieldType.OBJECT_ID)
    private String responseableEmployee;

    private Boolean isDeleted;
    private Boolean isBlocked;
    private Boolean isEmployee;
    private String password;
}
