package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case USERS_LIST:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getUsersListView());
                    break;
                default:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getRegisterUserView());
                    break;
            }
        });
    }
}
