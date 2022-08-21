package com.example.eshop.service;

import com.example.eshop.model.Account;
import com.example.eshop.model.dto.AccountDto;
import com.example.eshop.repository.AccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtos = List.of();
        accountRepo.findAll().forEach(
            (account -> {
                accountDtos.add(accountToAccountDto(account));
            })
        );
        return accountDtos;
    }



    private AccountDto accountToAccountDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomerId(account.getCustomerId());
        accountDto.setLastModifiedby(account.getLastModifiedBy());
        accountDto.setLastModifiedDate(new Date());
        accountDto.setIsBlocked(account.getIsBlocked());
        accountDto.setTotalAmount(account.getTotalAmount());

        return accountDto;
    }

}
