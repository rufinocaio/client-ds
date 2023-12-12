package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private BorderPane admin_parent;
    private MenuOptions previousMenu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            Model.getInstance().getViewFactory().resetAllAnchorPanes();
            if (newValue == MenuOptions.PREVIUS_MENU) {
                newValue = previousMenu;
            }
            if (newValue != MenuOptions.SELECT_POINT) {
                previousMenu = newValue;
            }
            switch (newValue) {
                // User CRUD
                case REGISTER_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getRegisterUserView());
                    break;
                case USER_LIST:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getUsersListView());
                    break;
                case EDIT_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getEditUserView());
                    break;
                case DELETE_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDeleteUserView());
                    break;
                // Point CRUD
                case REGISTER_POINT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getRegisterPointView());
                    break;
                case POINT_LIST:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getPointsListView());
                    break;
                case EDIT_POINT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getEditPointView());
                    break;
                case DELETE_POINT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDeletePointView());
                    break;
                // Segment CRUD
                case REGISTER_SEGMENT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getRegisterSegmentView());
                    break;
                case SEGMENT_LIST:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getSegmentsListView());
                    break;
                case EDIT_SEGMENT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getEditSegmentView());
                    break;
                case DELETE_SEGMENT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getDeleteSegmentView());
                    break;
                case SELECT_POINT:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getSelectPointView());
                    break;
                // Common
                case PROFILE:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getProfileView());
                    break;
                case AUTO_EDIT_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getAutoEditUserView());
                    break;
                case AUTO_DELETE_USER:
                    admin_parent.setCenter(Model.getInstance().getViewFactory().getAutoDeleteUserView());
                    break;
            }
        });
    }
}
