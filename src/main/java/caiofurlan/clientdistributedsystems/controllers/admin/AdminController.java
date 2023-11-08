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
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            Model.getInstance().getViewFactory().resetAllAnchorPanes();
            switch (newValue) {
                case USERS_LIST:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getUsersListView());
                    break;
                case PROFILE:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                    break;
                case EDIT_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getEditUserView());
                    break;
                case DELETE_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDeleteUserView());
                    break;
                case EDIT_USER_ADM:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getEditUserAdmView());
                    break;
                case DELETE_USER_ADM:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDeleteUserAdmView());
                    break;
                default:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getRegisterUserView());
                    break;
            }
        });
    }
}
