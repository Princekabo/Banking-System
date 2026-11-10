package banking.model;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String branch;
    private String customerId;

    public Account(String accountNumber, double balance, String branch, String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.branch = branch;
        this.customerId = customerId;
    }

    public void deposit(double amount) {
        if (amount > 0) balance = balance + amount;
    }

    public void deposit(double amount, String description) {
        deposit(amount); // overloading
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            return true;
        }
        return false;
    }

    public abstract double calculateInterest();
    public abstract String getType();

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getBranch() { return branch; }
    public String getCustomerId() { return customerId; }
}