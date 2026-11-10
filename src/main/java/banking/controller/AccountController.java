package banking.controller;

import banking.dao.AccountDAO;
import banking.model.*;
import java.util.ArrayList;

public class AccountController {
    private AccountDAO dao = new AccountDAO();

    public Account open(String type, String customerId, double amount,
                        String employerName, String employerAddress) {
        String number = "ACC" + System.currentTimeMillis() % 100000;
        Account a;
        if (type.equals("Savings"))
            a = new SavingsAccount(number, amount, "Gaborone", customerId);
        else if (type.equals("Investment"))
            a = new InvestmentAccount(number, amount, "Gaborone", customerId);
        else
            a = new ChequeAccount(number, amount, "Gaborone", customerId, employerName, employerAddress);
        dao.save(a);
        return a;
    }

    public boolean deposit(Account a, double amount) {
        if (amount <= 0) return false;
        a.deposit(amount);
        dao.updateBalance(a);
        return true;
    }

    public boolean withdraw(Account a, double amount) {
        if (a.withdraw(amount)) {
            dao.updateBalance(a);
            return true;
        }
        return false;
    }

    public ArrayList<Account> getAccounts(String customerId) {
        return dao.getByCustomer(customerId);
    }
}