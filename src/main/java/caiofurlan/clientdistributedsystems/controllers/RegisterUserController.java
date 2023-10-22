package caiofurlan.clientdistributedsystems.controllers;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {

    public TextField name_field;
    public TextField email_field;
    public PasswordField password_field;
    public Button register_button;
    public Label error_label;
    public Button login_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register_button.setOnAction(event -> {
            onRegister();
        });
        login_button.setOnAction(event -> onLogin());
    }

    private void onRegister() {
        SendData sender = new SendData();
        String response = sender.sendRegisterUser(name_field.getText(), email_field.getText(), DigestUtils.md5Hex(password_field.getText()).toUpperCase());
        if (response != null)
        {
            try {
                ReceiveData receiver = new ReceiveData(ReceiveData.stringToMap(response));
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                } else {
                    name_field.setText("");
                    email_field.setText("");
                    password_field.setText("");
                    error_label.setText("Usuário cadastrado com sucesso!");
                }
            } catch (JsonProcessingException e) {
                Model.getInstance().getViewFactory().showErrorMessage(e.getMessage());
            }
        }
    }

    private void onLogin() {
        Model.getInstance().getViewFactory().closeStage((Stage) login_button.getScene().getWindow());
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
