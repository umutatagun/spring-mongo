package com.example.eshop.model.dto;

import lombok.Data;

@Data
public class AddressDto extends BaseEntityDto {
    private String addressId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private Integer postCode;
}
