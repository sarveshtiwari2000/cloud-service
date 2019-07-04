package com.smava.services;

import com.smava.entities.Account;

public interface AccountService {

    Iterable<Account> listAllAccounts();

    Account getAccountById(Integer id);

    Account saveAccount(Account account);

    void deleteAccount(Integer id);

}
