package banking.view;

import banking.controller.AccountController;
import banking.model.Account;
import banking.model.Customer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class DashboardView {
    private AccountController controller = new AccountController();
    private ListView<String> list = new ListView<>();
    private ArrayList<Account> accounts;

    public void show(Stage stage, Customer customer) {
        accounts = controller.getAccounts(customer.getCustomerId());
        refreshList();

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Savings", "Investment", "Cheque");
        typeBox.setValue("Savings");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        Button openBtn = new Button("Open Account");

        TextField txField = new TextField();
        txField.setPromptText("Amount");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");

        Label msg = new Label();

        openBtn.setOnAction(e -> {
            try {
                double amt = Double.parseDouble(amountField.getText());
                String empName = null, empAddr = null;
                if (typeBox.getValue().equals("Cheque")) {
                    empName = "Acme Ltd";
                    empAddr = "Gaborone";
                }
                Account a = controller.open(typeBox.getValue(),
                        customer.getCustomerId(), amt, empName, empAddr);
                accounts.add(a);
                refreshList();
                msg.setText("Account opened: " + a.getAccountNumber());
            } catch (Exception ex) {
                msg.setText("Error: " + ex.getMessage());
            }
        });

        depositBtn.setOnAction(e -> doTx(txField, msg, true));
        withdrawBtn.setOnAction(e -> doTx(txField, msg, false));

        VBox root = new VBox(8,
                new Label("Welcome " + customer.getFirstName()),
                new Label("Your Accounts:"),
                list,
                new Label("Open New Account:"),
                typeBox, amountField, openBtn,
                new Label("Deposit / Withdraw:"),
                txField, depositBtn, withdrawBtn,
                msg);
        root.setPadding(new Insets(15));

        stage.setScene(new Scene(root, 450, 600));
        stage.setTitle("Dashboard");
        stage.show();
    }

    private void doTx(TextField field, Label msg, boolean isDeposit) {
        int index = list.getSelectionModel().getSelectedIndex();
        if (index < 0) { msg.setText("Select an account"); return; }
        try {
            double amt = Double.parseDouble(field.getText());
            Account a = accounts.get(index);
            boolean ok = isDeposit ? controller.deposit(a, amt) : controller.withdraw(a, amt);
            msg.setText(ok ? "Done" : "Rejected");
            refreshList();
        } catch (NumberFormatException e) {
            msg.setText("Enter a number");
        }
    }

    private void refreshList() {
        list.getItems().clear();
        for (Account a : accounts) {
            list.getItems().add(a.getType() + " | " + a.getAccountNumber()
                    + " | BWP " + a.getBalance());
        }
    }
}