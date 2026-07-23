package com.mehmetserin.bankmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Customer {
    protected String name;
    protected String address;
    protected String contactDetails;
    private final List<Account> accounts = new ArrayList<>();
    private final FinancialPortfolio portfolio = new FinancialPortfolio();

    protected Customer(String name, String address, String contactDetails) {
        this.name = Objects.requireNonNull(name, "Name is required");
        this.address = address;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public FinancialPortfolio getPortfolio() {
        return portfolio;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name is required");
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        if (!accounts.contains(account)) {
            accounts.add(account);
            portfolio.addAccount(account);
        }
    }

    public void removeAccount(Account account) {
        if (account == null) {
            return;
        }
        accounts.remove(account);
        portfolio.removeAccount(account);
    }
}
