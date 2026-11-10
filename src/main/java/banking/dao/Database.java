package banking.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    public static Connection connect() throws Exception {
        return DriverManager.getConnection("jdbc:h2:./bankdb", "sa", "");
    }

    public static void setup() {
        try (Connection con = connect(); Statement st = con.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS customers (" +
                    "customer_id VARCHAR(20) PRIMARY KEY," +
                    "first_name VARCHAR(50)," +
                    "surname VARCHAR(50)," +
                    "address VARCHAR(100)," +
                    "password VARCHAR(50))");

            st.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                    "account_number VARCHAR(20) PRIMARY KEY," +
                    "customer_id VARCHAR(20)," +
                    "type VARCHAR(20)," +
                    "balance DOUBLE," +
                    "branch VARCHAR(50)," +
                    "employer_name VARCHAR(100)," +
                    "employer_address VARCHAR(100))");

            System.out.println("Database ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}