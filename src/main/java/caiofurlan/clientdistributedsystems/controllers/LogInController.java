package caiofurlan.clientdistributedsystems.controllers;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendLogin;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public TextField email_field;
    public TextField password_field;
    public Button login_button;
    public Label error_label;
    public Button register_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email_field.setText("admin@admin.com");
        password_field.setText("admin123");
        login_button.setOnAction(event -> {
            try {
                onLogin();
            } catch (Exception e) {
                setError_label("Dados inválidos");
                throw new RuntimeException(e);
            }
        });
        register_button.setOnAction(event -> onRegister());
    }

    private void onRegister() {
        Model.getInstance().getViewFactory().closeStage((Stage) login_button.getScene().getWindow());
        Model.getInstance().getViewFactory().showRegisterWindow();
    }

    private void onLogin() throws Exception {
        SendLogin sender = new SendLogin();
        String email = email_field.getText();
        String password = password_field.getText();
        if (IsValid.loginIsValid(email, password)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sender.send(email, password);
            if (response != null) {
                Receiver receiver = new Receiver(response);
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                } else {
                    Stage stage = (Stage) error_label.getScene().getWindow();
                    TokenManager.saveToken(receiver.getToken());
                    if (TokenManager.isTokenAdmin(TokenManager.getToken())) {
                        Model.getInstance().getViewFactory().showAdminWindow();
                    } else {
                        Model.getInstance().getViewFactory().showUserWindow();
                    }
                    Model.getInstance().getViewFactory().closeStage(stage);
                }
            }
        }
    }

    private void setError_label(String message) {
        error_label.setText(message);
    }
}
