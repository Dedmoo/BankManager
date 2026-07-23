package com.mehmetserin.bankmanager;

public interface TransactionExecution {
    void deposit(Account account, double amount);

    void withdraw(Account account, double amount);

    void transfer(Account from, Account to, double amount);
}
