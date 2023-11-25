package caiofurlan.clientdistributedsystems.controllers.common;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.usercrud.SendEditUser;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    public TextField name_field;
    public TextField email_field;
    public PasswordField password_field;
    public Button save_button;
    public Label error_label;
    public Button left_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserInfo(Model.getInstance().getUser());
        save_button.setOnAction(event -> {
            try {
                onSaveUser();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        left_button.setOnAction(event -> onLeft());
    }

    public void setUserInfo(User user) {
        name_field.setText(user.getName());
        email_field.setText(user.getEmail());
        password_field.setText("--------");
    }

    private void onSaveUser() {
        SendEditUser sender = new SendEditUser();
        String name = name_field.getText();
        String password = password_field.getText();
        String email = email_field.getText();
        try {
            if (IsValid.editUserIsValid(name, email, password, Model.getInstance().getUser().getType())) {
                password = DigestUtils.md5Hex(password).toUpperCase();
                JsonNode response = sender.send(TokenManager.getToken(), Model.getInstance().getUser().getID(), name, email, password);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                    } else {
                        error_label.setText("Usuário editado com sucesso");
                    }
                }
            }
        } catch (Exception e){
            Model.getInstance().getViewFactory().showErrorMessage(e.getMessage());
        }

    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PROFILE);
    }
}
