package caiofurlan.clientdistributedsystems.controllers.common;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProfileController implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField email_field;
    @FXML
    private TextField password_field;
    @FXML
    private Button delete_button;
    @FXML
    private Button edit_button;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        setUserInfo(Model.getInstance().getSelfUser());
        edit_button.setOnAction(event -> {
            try {
                onEditUser();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        delete_button.setOnAction(event -> onDeleteUser());
    }

    public void setUserInfo(User user) {
        name_field.setText(user.getName());
        name_field.setEditable(false);
        email_field.setText(user.getEmail());
        name_field.setEditable(false);
        password_field.setText("--------");
        name_field.setEditable(false);
    }

    private void onEditUser() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.AUTO_EDIT_USER);
    }

    private void onDeleteUser() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.AUTO_DELETE_USER);
    }

}
