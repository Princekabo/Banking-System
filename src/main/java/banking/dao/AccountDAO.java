package banking.dao;

import banking.model.*;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    public void save(Account a) {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO accounts VALUES (?,?,?,?,?,?,?)")) {
            ps.setString(1, a.getAccountNumber());
            ps.setString(2, a.getCustomerId());
            ps.setString(3, a.getType());
            ps.setDouble(4, a.getBalance());
            ps.setString(5, a.getBranch());
            if (a instanceof ChequeAccount) {
                ChequeAccount ca = (ChequeAccount) a;
                ps.setString(6, ca.getEmployerName());
                ps.setString(7, ca.getEmployerAddress());
            } else {
                ps.setString(6, null);
                ps.setString(7, null);
            }
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateBalance(Account a) {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE accounts SET balance=? WHERE account_number=?")) {
            ps.setDouble(1, a.getBalance());
            ps.setString(2, a.getAccountNumber());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public ArrayList<Account> getByCustomer(String customerId) {
        ArrayList<Account> list = new ArrayList<>();
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM accounts WHERE customer_id=?")) {
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("type");
                String no = rs.getString("account_number");
                double bal = rs.getDouble("balance");
                String br = rs.getString("branch");
                if (type.equals("Savings"))
                    list.add(new SavingsAccount(no, bal, br, customerId));
                else if (type.equals("Investment"))
                    list.add(new InvestmentAccount(no, bal, br, customerId));
                else
                    list.add(new ChequeAccount(no, bal, br, customerId,
                            rs.getString("employer_name"),
                            rs.getString("employer_address")));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}