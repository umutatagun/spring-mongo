package com.example.eshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
public class Address extends BaseEntity{

    @Id
    @Field(targetType = FieldType.OBJECT_ID)
    private String addressId;

    private String city;
    private Integer postCode;
    private String state;
    private String address1;
    private String address2;

    @Field(targetType = FieldType.OBJECT_ID)
    private String customerId;

}
