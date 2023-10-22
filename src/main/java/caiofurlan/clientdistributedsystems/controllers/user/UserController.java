package caiofurlan.clientdistributedsystems.controllers.user;

import caiofurlan.clientdistributedsystems.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    public BorderPane user_parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                default:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    break;
            }
        });
    }
}
