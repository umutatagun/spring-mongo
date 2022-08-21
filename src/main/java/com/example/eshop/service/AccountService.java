package com.example.eshop.service;

import com.example.eshop.exception.AccountAlreadyExistsException;
import com.example.eshop.exception.AccountNotFoundException;
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

    public AccountDto getAccountById(String id) {
        return accountToAccountDto(findByAccountId(id));
    }

    public AccountDto addAccount(Account account) {
        if(accountRepo.findById(account.getAccountId()).isPresent()){
            throw new AccountAlreadyExistsException("Account already exists with id "+account.getAccountId());
        }
        accountRepo.save(account);
        return accountToAccountDto(account);
    }

    public AccountDto updateAccount(String id, Account account) {
        Account ac = findByAccountId(id);
        ac.setCustomerId(account.getCustomerId());
        ac.setIsBlocked(account.getIsBlocked());
        ac.setTotalAmount(account.getTotalAmount());
        ac.setLastModifiedDate(new Date());
        ac.setLastModifiedBy(account.getLastModifiedBy());

        accountRepo.save(ac);

        return accountToAccountDto(ac);
    }

    public void deleteAccountById(String id){
        Account account = findByAccountId(id);
        accountRepo.delete(account);
    }

    public void deleteAccountsByCustomerId(String customerId) {
        accountRepo.deleteAllByCustomerId(customerId);
    }


    private Account findByAccountId(String id)  {
        return accountRepo.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id "+id));
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
