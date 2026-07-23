package com.mehmetserin.bankmanager;

public final class DemoApp {
    private DemoApp() {
    }

    public static void main(String[] args) {
        BankManager bank = new BankManager();

        IndividualCustomer customer1 = new IndividualCustomer(
                "Doga Sutasir",
                "Agva Caddesi Sile",
                "053225323",
                "Female",
                "ID7122",
                "Computer Engineer");

        IndividualCustomer customer2 = new IndividualCustomer(
                "Mehmet Serin",
                "Balibey Mah. Sile",
                "05342323",
                "Male",
                "ID6366",
                "Software Engineer");

        Account savings1 = bank.createAccount(customer1, "TEB7762", 1000.0, "Savings");
        Account savings2 = bank.createAccount(customer1, "TEB7763", 1500.0, "Savings");
        Account checking1 = bank.createAccount(customer1, "TEB7761", 500.0, "Checking");
        Account checking2 = bank.createAccount(customer2, "TEB7764", 2000.0, "Checking");

        bank.deposit(savings1, 300.0);
        bank.deposit(savings2, 400.0);
        bank.deposit(checking1, 500.0);

        System.out.println(savings1);
        System.out.println(savings2);
        System.out.println(checking1);
        System.out.println("Portfolio 1 total: $" + customer1.getPortfolio().calculateTotalValue());

        customer1.getPortfolio().removeAccount(savings2);
        System.out.println("Portfolio 1 after removal: $" + customer1.getPortfolio().calculateTotalValue());

        bank.deposit(checking2, 600.0);
        bank.transfer(checking1, checking2, 500.0);

        System.out.println(checking1);
        System.out.println(checking2);
        System.out.println("Portfolios equal? " + customer1.getPortfolio().comparePortfolio(customer2.getPortfolio()));
        System.out.println("Portfolio 1 total: $" + customer1.getPortfolio().calculateTotalValue());
        System.out.println("Portfolio 2 total: $" + customer2.getPortfolio().calculateTotalValue());
    }
}
