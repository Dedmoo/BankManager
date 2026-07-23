package com.mehmetserin.bankmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BankManager implements AccountManagement, TransactionExecution {
    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account createAccount(Customer owner, String accountNumber, double initialBalance, String accountType) {
        Objects.requireNonNull(owner, "Owner is required");
        if (findByNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Account number already exists: " + accountNumber);
        }

        Account account;
        if ("Savings".equalsIgnoreCase(accountType)) {
            account = new SavingsAccount(accountNumber, initialBalance, owner, 1.5);
        } else if ("Checking".equalsIgnoreCase(accountType)) {
            account = new CheckingAccount(accountNumber, initialBalance, owner, true, true, true);
        } else {
            throw new IllegalArgumentException("Unsupported account type: " + accountType);
        }

        accounts.add(account);
        owner.addAccount(account);
        return account;
    }

    @Override
    public void deleteAccount(Account account) {
        Objects.requireNonNull(account, "Account is required");
        if (!accounts.remove(account)) {
            throw new IllegalArgumentException("Account is not managed by this bank");
        }
        account.getOwner().removeAccount(account);
    }

    @Override
    public void displayAccountDetails(Account account) {
        System.out.println(account);
    }

    @Override
    public void updateContactDetails(Customer customer, String newDetails) {
        Objects.requireNonNull(customer, "Customer is required");
        if (newDetails == null || newDetails.isBlank()) {
            throw new IllegalArgumentException("New contact details are required");
        }
        customer.setContactDetails(newDetails.trim());
    }

    @Override
    public void deposit(Account account, double amount) {
        requireManaged(account);
        requirePositive(amount);
        account.credit(amount, "DEPOSIT");
    }

    @Override
    public void withdraw(Account account, double amount) {
        requireManaged(account);
        requirePositive(amount);
        if (account.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        account.debit(amount, "WITHDRAW");
    }

    @Override
    public void transfer(Account from, Account to, double amount) {
        requireManaged(from);
        requireManaged(to);
        requirePositive(amount);
        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (from.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        from.debit(amount, "TRANSFER_OUT");
        to.credit(amount, "TRANSFER_IN");
    }

    public Optional<Account> findByNumber(String accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    private void requireManaged(Account account) {
        Objects.requireNonNull(account, "Account is required");
        if (!accounts.contains(account)) {
            throw new IllegalArgumentException("Account is not managed by this bank");
        }
    }

    private static void requirePositive(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
