package banking;

import banking.dao.Database;
import banking.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Database.setup();
        new LoginView().show(stage);
    }
    public static void main(String[] args) { launch(args); }
}