package com.example.eshop.model.dto;

import lombok.Data;

@Data
public class AccountDto extends BaseEntityDto{
    private String accountId;
    private Integer totalAmount;
    private Boolean isBlocked;
    private String customerId;

}
