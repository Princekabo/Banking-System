package banking.dao;

import banking.model.Customer;
import java.sql.*;

public class CustomerDAO {

    public void save(Customer c) {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO customers VALUES (?,?,?,?,?)")) {
            ps.setString(1, c.getCustomerId());
            ps.setString(2, c.getFirstName());
            ps.setString(3, c.getSurname());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getPassword());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Customer login(String id, String password) {
        try (Connection con = Database.connect();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM customers WHERE customer_id=? AND password=?")) {
            ps.setString(1, id);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(rs.getString("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address"),
                        rs.getString("password"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}