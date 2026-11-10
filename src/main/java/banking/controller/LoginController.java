package banking.controller;

import banking.dao.CustomerDAO;
import banking.model.Customer;

public class LoginController {
    private CustomerDAO dao = new CustomerDAO();

    public Customer login(String id, String password) {
        if (id.isEmpty() || password.isEmpty()) return null;
        return dao.login(id, password);
    }

    public boolean register(String id, String first, String surname, String address, String password) {
        if (id.isEmpty() || first.isEmpty() || surname.isEmpty() || password.isEmpty()) return false;
        dao.save(new Customer(id, first, surname, address, password));
        return true;
    }
}