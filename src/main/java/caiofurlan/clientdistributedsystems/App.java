package caiofurlan.clientdistributedsystems;

import caiofurlan.clientdistributedsystems.models.Connection;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static caiofurlan.clientdistributedsystems.system.connection.Connection connection = new caiofurlan.clientdistributedsystems.system.connection.Connection();
    private final Token tokenHelper = new Token();

    private Stage primaryStage = null;
    private static App instance;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        openConnectWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openConnectWindow() {
        Model.getInstance().getViewFactory().showConnectWindow();
    }

    public static Connection openConnection(String ip, String port) {
        try {
            return new Connection(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void restart() throws Exception {
        primaryStage.close();
        Token.eraseJwtToken();
        this.start(primaryStage);
    }

    public static caiofurlan.clientdistributedsystems.system.connection.Connection getConnection() {
        return connection;
    }

    public static App getInstace() {
        return instance;
    }

}
