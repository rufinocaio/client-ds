package caiofurlan.clientdistributedsystems.controllers.user;

import caiofurlan.clientdistributedsystems.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private BorderPane user_parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            Model.getInstance().getViewFactory().resetAllAnchorPanes();
            switch (newValue) {
                case PROFILE:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                    break;
                case AUTO_EDIT_USER:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getAutoEditUserView());
                    break;
                case AUTO_DELETE_USER:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getAutoDeleteUserView());
                    break;
                case SELECT_POINT:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getSelectPointView());
                    break;
                default:
                    user_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
                    break;
            }
        });
    }
}
