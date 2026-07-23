package com.mehmetserin.bankmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FinancialPortfolio {
    private static final double VALUE_EPSILON = 0.0001;

    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        if (account != null && !accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public double calculateTotalValue() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public void printPortfolioDetails() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public boolean comparePortfolio(FinancialPortfolio other) {
        if (other == null) {
            return false;
        }
        return Math.abs(calculateTotalValue() - other.calculateTotalValue()) < VALUE_EPSILON;
    }
}
