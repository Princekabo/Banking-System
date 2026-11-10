package banking.model;

public class InvestmentAccount extends Account implements InterestBearing {

    public InvestmentAccount(String accountNumber, double balance, String branch, String customerId) {
        super(accountNumber, balance, branch, customerId);
        if (balance < 500) {
            throw new IllegalArgumentException("Investment needs BWP 500 to open");
        }
    }

    @Override
    public double calculateInterest() {
        return getBalance() * 0.05; // 5%
    }

    @Override
    public String getType() { return "Investment"; }
}