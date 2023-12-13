package caiofurlan.clientdistributedsystems.controllers.common.autousercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.autousercrud.SendAutoEditUser;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoEditUserController implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button save_button;
    @FXML
    private Label error_label;
    @FXML
    private Button left_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserInfo(Model.getInstance().getSelfUser());
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
        SendAutoEditUser sender = new SendAutoEditUser();
        String name = name_field.getText();
        String password = password_field.getText();
        String email = email_field.getText();
        try {
            if (DataValidation.userInfoValidation(name, email, password, Model.getInstance().getSelfUser().getType())) {
                password = DigestUtils.md5Hex(password).toUpperCase();
                JsonNode response = sender.send(Model.getInstance().getSelfUser().getID(), name, email, password);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                    } else {
                        error_label.setText("Usuário editado com sucesso");
                    }
                }
            }
        } catch (Exception e){
            Model.getInstance().getViewFactory().showErrorWindow(e.getMessage());
        }

    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PROFILE);
    }
}
