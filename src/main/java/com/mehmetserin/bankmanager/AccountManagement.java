package com.mehmetserin.bankmanager;

public interface AccountManagement {
    Account createAccount(Customer owner, String accountNumber, double initialBalance, String accountType);

    void deleteAccount(Account account);

    void displayAccountDetails(Account account);

    void updateContactDetails(Customer customer, String newDetails);
}
