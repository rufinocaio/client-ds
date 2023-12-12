package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.usercrud.SendRegisterUser;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUserController implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField email_field;
    @FXML
    private TextField password_field;
    @FXML
    private ChoiceBox account_selector;
    @FXML
    private Button register_button;
    @FXML
    private Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account_selector.setItems(FXCollections.observableArrayList("Usuário", "Administrador"));
        register_button.setOnAction(event -> {
            try {
                onRegister();
            } catch (Exception e) {
                setError_label("Dados inválidos");
                throw new RuntimeException(e);
            }
        });
    }

    private void onRegister() throws Exception {
        SendRegisterUser sender = new SendRegisterUser();
        String userType = account_selector.getValue().toString().equals("Administrador") ? "admin" : "user";
        String name = name_field.getText();
        String email = email_field.getText();
        String password = password_field.getText();
        if (DataValidation.userInfoValidation(name, email, password, userType)) {
            password = DigestUtils.md5Hex(password).toUpperCase();
            JsonNode response = sender.send(name, email, password, userType);
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

    private void setError_label(String message) {
        error_label.setText(message);
    }
}
