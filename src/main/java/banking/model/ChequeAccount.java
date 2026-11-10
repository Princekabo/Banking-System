package banking.model;

public class ChequeAccount extends Account {
    private String employerName;
    private String employerAddress;

    public ChequeAccount(String accountNumber, double balance, String branch,
                         String customerId, String employerName, String employerAddress) {
        super(accountNumber, balance, branch, customerId);
        if (employerName == null || employerAddress == null) {
            throw new IllegalArgumentException("Employer details required");
        }
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public double calculateInterest() { return 0; }

    @Override
    public String getType() { return "Cheque"; }

    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }
}