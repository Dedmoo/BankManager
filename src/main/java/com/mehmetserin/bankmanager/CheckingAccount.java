package com.mehmetserin.bankmanager;

public class CheckingAccount extends Account {
    private static final double ANNUAL_INTEREST_RATE_PERCENT = 3.0;

    private boolean hasDebitCard;
    private boolean atmAccess;
    private boolean onlineBankingAccess;

    public CheckingAccount(
            String accountNumber,
            double initialBalance,
            Customer owner,
            boolean hasDebitCard,
            boolean atmAccess,
            boolean onlineBankingAccess) {
        super(accountNumber, initialBalance, owner);
        this.hasDebitCard = hasDebitCard;
        this.atmAccess = atmAccess;
        this.onlineBankingAccess = onlineBankingAccess;
    }

    public boolean hasDebitCard() {
        return hasDebitCard;
    }

    public boolean hasATMAccess() {
        return atmAccess;
    }

    public boolean hasOnlineBankingAccess() {
        return onlineBankingAccess;
    }

    public void setHasDebitCard(boolean hasDebitCard) {
        this.hasDebitCard = hasDebitCard;
    }

    public void setATMAccess(boolean atmAccess) {
        this.atmAccess = atmAccess;
    }

    public void setOnlineBankingAccess(boolean onlineBankingAccess) {
        this.onlineBankingAccess = onlineBankingAccess;
    }

    @Override
    public void applyInterest() {
        balance += balance * ANNUAL_INTEREST_RATE_PERCENT / 100.0;
    }

    @Override
    public double calculateFutureBalance() {
        return balance * (1 + ANNUAL_INTEREST_RATE_PERCENT / 100.0);
    }
}
