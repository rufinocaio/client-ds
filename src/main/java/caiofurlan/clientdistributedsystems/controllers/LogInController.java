package caiofurlan.clientdistributedsystems.controllers;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.IsValidData;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
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
        SendData sender = new SendData();
        String email = email_field.getText();
        String password = password_field.getText();
        if (IsValidData.loginIsValid(email, password)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            String response = sender.sendLogin(email, password);
            if (response != null) {
                ReceiveData receiver = new ReceiveData(ReceiveData.stringToMap(response));
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                } else {
                    Stage stage = (Stage) error_label.getScene().getWindow();
                    Token.saveJwtToken(receiver.getToken());
                    if (Token.isTokenAdmin(Token.getJwtToken())) {
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
