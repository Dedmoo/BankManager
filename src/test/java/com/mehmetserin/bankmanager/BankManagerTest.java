package com.mehmetserin.bankmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankManagerTest {
    private BankManager bank;
    private IndividualCustomer alice;
    private IndividualCustomer bob;

    @BeforeEach
    void setUp() {
        bank = new BankManager();
        alice = new IndividualCustomer("Alice", "Street 1", "alice@example.com", "F", "ID1", "Engineer");
        bob = new IndividualCustomer("Bob", "Street 2", "bob@example.com", "M", "ID2", "Analyst");
    }

    @Test
    void createAccountLinksCustomerAndPortfolio() {
        Account savings = bank.createAccount(alice, "SAV-1", 1000, "Savings");

        assertEquals(1, bank.getAccounts().size());
        assertEquals(1, alice.getAccounts().size());
        assertEquals(1000, alice.getPortfolio().calculateTotalValue(), 0.0001);
        assertEquals(savings, alice.getAccounts().get(0));
    }

    @Test
    void depositAndWithdrawUpdateBalanceAndHistory() {
        Account checking = bank.createAccount(alice, "CHK-1", 200, "Checking");

        bank.deposit(checking, 50);
        bank.withdraw(checking, 30);

        assertEquals(220, checking.getBalance(), 0.0001);
        assertEquals(2, checking.getTransactionHistory().size());
        assertEquals("DEPOSIT", checking.getTransactionHistory().get(0).getType());
        assertEquals("WITHDRAW", checking.getTransactionHistory().get(1).getType());
    }

    @Test
    void withdrawRejectsInsufficientFunds() {
        Account checking = bank.createAccount(alice, "CHK-2", 40, "Checking");

        assertThrows(IllegalStateException.class, () -> bank.withdraw(checking, 50));
        assertEquals(40, checking.getBalance(), 0.0001);
        assertTrue(checking.getTransactionHistory().isEmpty());
    }

    @Test
    void transferMovesMoneyBetweenManagedAccounts() {
        Account from = bank.createAccount(alice, "CHK-3", 500, "Checking");
        Account to = bank.createAccount(bob, "CHK-4", 100, "Checking");

        bank.transfer(from, to, 150);

        assertEquals(350, from.getBalance(), 0.0001);
        assertEquals(250, to.getBalance(), 0.0001);
        assertEquals("TRANSFER_OUT", from.getTransactionHistory().get(0).getType());
        assertEquals("TRANSFER_IN", to.getTransactionHistory().get(0).getType());
    }

    @Test
    void deleteAccountRemovesFromBankAndCustomer() {
        Account savings = bank.createAccount(alice, "SAV-2", 800, "Savings");

        bank.deleteAccount(savings);

        assertTrue(bank.getAccounts().isEmpty());
        assertTrue(alice.getAccounts().isEmpty());
        assertEquals(0, alice.getPortfolio().calculateTotalValue(), 0.0001);
    }

    @Test
    void duplicateAccountNumberIsRejected() {
        bank.createAccount(alice, "DUP-1", 10, "Checking");
        assertThrows(IllegalArgumentException.class, () -> bank.createAccount(bob, "DUP-1", 20, "Savings"));
    }

    @Test
    void savingsInterestUsesPercentRate() {
        SavingsAccount savings = (SavingsAccount) bank.createAccount(alice, "SAV-3", 1000, "Savings");
        savings.setInterestRate(2.0);

        assertEquals(1020, savings.calculateFutureBalance(), 0.0001);
        savings.applyInterest();
        assertEquals(1020, savings.getBalance(), 0.0001);
    }

    @Test
    void portfolioCompareUsesTotalValue() {
        Account a1 = bank.createAccount(alice, "A1", 300, "Checking");
        Account b1 = bank.createAccount(bob, "B1", 300, "Checking");

        assertTrue(alice.getPortfolio().comparePortfolio(bob.getPortfolio()));
        bank.deposit(a1, 1);
        assertFalse(alice.getPortfolio().comparePortfolio(bob.getPortfolio()));
        assertEquals(b1.getBalance(), 300, 0.0001);
    }
}
