package com.mehmetserin.bankmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Account {
    protected final String accountNumber;
    protected double balance;
    protected final Customer owner;
    protected final List<Transaction> transactionHistory = new ArrayList<>();

    protected Account(String accountNumber, double initialBalance, Customer owner) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.owner = Objects.requireNonNull(owner, "Owner is required");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    void credit(double amount, String type) {
        balance += amount;
        transactionHistory.add(new Transaction(type, amount));
    }

    void debit(double amount, String type) {
        balance -= amount;
        transactionHistory.add(new Transaction(type, amount));
    }

    public abstract void applyInterest();

    public abstract double calculateFutureBalance();

    public String getLastTwoTransactions() {
        int historySize = transactionHistory.size();
        int start = Math.max(0, historySize - 2);
        StringBuilder output = new StringBuilder();
        for (int i = start; i < historySize; i++) {
            output.append(transactionHistory.get(i)).append('\n');
        }
        return output.toString();
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber
                + "\nBalance: " + balance
                + "\nLast Transactions:\n" + getLastTwoTransactions();
    }
}
