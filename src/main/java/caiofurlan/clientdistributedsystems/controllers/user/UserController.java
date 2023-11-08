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
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            Model.getInstance().getViewFactory().resetAllAnchorPanes();
            switch (newValue) {
                case PROFILE:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                    break;
                case EDIT_USER:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getEditUserView());
                    break;
                case DELETE_USER:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getDeleteUserView());
                    break;
                default:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    break;
            }
        });
    }
}
