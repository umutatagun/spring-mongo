package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.NotNull;

@Data
public class Account extends BaseEntity{
    @Field(targetType = FieldType.OBJECT_ID)
    @Id
    private String accountId;

    private Integer totalAmount = 0;
    private Boolean isBlocked = false;

    @Field(targetType = FieldType.OBJECT_ID)
    private String customerId;
}
