package caiofurlan.clientdistributedsystems;

import caiofurlan.clientdistributedsystems.models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        openConnectWindow();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openConnectWindow() {
        Model.getInstance().getViewFactory().showConnectWindow();
    }

}
