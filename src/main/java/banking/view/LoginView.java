package banking.view;

import banking.controller.LoginController;
import banking.model.Customer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private LoginController controller = new LoginController();

    public void show(Stage stage) {
        TextField idField = new TextField();
        idField.setPromptText("Customer ID");

        PasswordField pwField = new PasswordField();
        pwField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("Register");
        Label msg = new Label();

        loginBtn.setOnAction(e -> {
            Customer c = controller.login(idField.getText(), pwField.getText());
            if (c != null) {
                new DashboardView().show(stage, c);
            } else {
                msg.setText("Wrong ID or password");
            }
        });

        registerBtn.setOnAction(e -> {
            boolean ok = controller.register(idField.getText(), "New", "User",
                    "Gaborone", pwField.getText());
            msg.setText(ok ? "Registered! Now login." : "Fill all fields");
        });

        VBox root = new VBox(10,
                new Label("Bank Login"),
                idField, pwField, loginBtn, registerBtn, msg);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Login");
        stage.show();
    }
}