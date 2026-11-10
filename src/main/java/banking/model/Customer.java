package banking.model;

import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String firstName;
    private String surname;
    private String address;
    private String password;
    private ArrayList<Account> accounts = new ArrayList<>();

    public Customer(String customerId, String firstName, String surname, String address, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.password = password;
    }

    public void addAccount(Account a) { accounts.add(a); }
    public ArrayList<Account> getAccounts() { return accounts; }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
}