package caiofurlan.clientdistributedsystems.controllers.common.autousercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.autousercrud.SendAutoRegisterUser;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoRegisterUserController implements Initializable {

    @FXML
    private TextField name_field;
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button register_button;
    @FXML
    private Label error_label;
    @FXML
    private Button login_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        register_button.setOnAction(event -> {
            try {
                onRegister();
            } catch (Exception e) {
                setError_label("Dados inválidos");
                throw new RuntimeException(e);
            }
        });
        login_button.setOnAction(event -> onLogin());
    }

    private void onRegister() throws Exception {
        SendAutoRegisterUser sender = new SendAutoRegisterUser();
        String name = name_field.getText();
        String email = email_field.getText();
        String password = DigestUtils.md5Hex(password_field.getText()).toUpperCase();
        if(DataValidation.userInfoValidation(name, email, password, "user")) {
            JsonNode response = sender.send(name, email, password);
            if (response != null)
            {
                Receiver receiver = new Receiver(response);
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                } else {
                    name_field.setText("");
                    email_field.setText("");
                    password_field.setText("");
                    error_label.setText("Usuário cadastrado com sucesso!");
                }
            }
        }
    }

    private void onLogin() {
        Model.getInstance().getViewFactory().closeStage((Stage) login_button.getScene().getWindow());
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private void setError_label(String message) {
        error_label.setText(message);
    }
}
