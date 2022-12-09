package com.example.demo.services;

import com.example.demo.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.RuntimeOperationsException;
import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        throw new RuntimeException("method not implemented!!!");
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        throw new RuntimeException("method not implemented!!!");
    }
}
