package banking.model;

public class SavingsAccount extends Account implements InterestBearing {

    public SavingsAccount(String accountNumber, double balance, String branch, String customerId) {
        super(accountNumber, balance, branch, customerId);
    }

    @Override
    public boolean withdraw(double amount) {
        return false; // NOT allowed
    }

    @Override
    public double calculateInterest() {
        return getBalance() * 0.0005; // 0.05%
    }

    @Override
    public String getType() { return "Savings"; }
}