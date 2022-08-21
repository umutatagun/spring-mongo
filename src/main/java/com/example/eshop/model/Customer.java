package com.example.eshop.model;

import com.example.eshop.model.enums.CustomerLevel;
import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Document
public class Customer extends BaseEntity {

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String customerId;

    private String customerName;
    private String customerSurname;
    private Integer age;
    private CustomerLevel customerLevel = CustomerLevel.NEW;
    @NotNull(message = "Email cannot be null")
    private String email;
    private List<Address> addresses;
    private List<Account> accounts;

    @Field(targetType = FieldType.OBJECT_ID)
    @Nullable
    private String responseableEmployee;

    private Boolean isDeleted = false;
    private Boolean isBlocked = false;
    private Boolean isEmployee = false;
    @NotNull(message = "Password cannot be null")
    private String password;
}
