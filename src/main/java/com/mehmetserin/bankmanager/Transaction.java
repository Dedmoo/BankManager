package com.mehmetserin.bankmanager;

import java.time.Instant;
import java.util.Objects;

public final class Transaction {
    private final String type;
    private final double amount;
    private final Instant transactionDate;

    public Transaction(String type, double amount) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Transaction type is required");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be positive");
        }
        this.type = type;
        this.amount = amount;
        this.transactionDate = Instant.now();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction: " + type + " of $" + amount + " on " + transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction that)) {
            return false;
        }
        return Double.compare(that.amount, amount) == 0
                && Objects.equals(type, that.type)
                && Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, transactionDate);
    }
}
