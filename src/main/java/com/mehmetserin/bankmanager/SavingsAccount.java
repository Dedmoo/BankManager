package com.mehmetserin.bankmanager;

public class SavingsAccount extends Account {
    private double annualInterestRatePercent;
    private String accountStatus;

    public SavingsAccount(String accountNumber, double initialBalance, Customer owner, double annualInterestRatePercent) {
        super(accountNumber, initialBalance, owner);
        if (annualInterestRatePercent < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.annualInterestRatePercent = annualInterestRatePercent;
        this.accountStatus = "Active";
    }

    public double getInterestRate() {
        return annualInterestRatePercent;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setInterestRate(double annualInterestRatePercent) {
        if (annualInterestRatePercent < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.annualInterestRatePercent = annualInterestRatePercent;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public void applyInterest() {
        balance += balance * annualInterestRatePercent / 100.0;
    }

    @Override
    public double calculateFutureBalance() {
        return balance * (1 + annualInterestRatePercent / 100.0);
    }
}
