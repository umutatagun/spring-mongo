package com.example.eshop.model.dto;

import com.example.eshop.model.Account;
import com.example.eshop.model.Address;
import com.example.eshop.model.enums.CustomerLevel;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto extends BaseEntityDto {
    private String customerId;
    private String customerName;
    private String customerSurname;
    private Integer age;
    private CustomerLevel customerLevel;
    private String email;
    private String accountId;
    private String responseableEmployee;
    private Boolean isDeleted;
    private Boolean isBlocked;
    private Boolean isEmployee;
    private List<Address> addresses;
    private List<Account> accounts;
}
