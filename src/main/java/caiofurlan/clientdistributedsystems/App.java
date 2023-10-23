package caiofurlan.clientdistributedsystems;

import caiofurlan.clientdistributedsystems.models.ConnectionModel;
import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.Connection;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static Connection connection = new Connection();
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

    public static ConnectionModel openConnection(String ip, String port) {
        try {
            return new ConnectionModel(ip, port);
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

    public static Connection getConnection() {
        return connection;
    }

    public static App getInstace() {
        return instance;
    }

}
