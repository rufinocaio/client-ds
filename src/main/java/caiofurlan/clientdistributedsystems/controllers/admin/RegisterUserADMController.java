package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUserADMController implements Initializable {
    public TextField name_field;
    public TextField email_field;
    public TextField password_field;
    public ChoiceBox account_selector;
    public Button register_button;
    public Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        account_selector.setItems(FXCollections.observableArrayList("Usuário", "Administrador"));
        register_button.setOnAction(event -> {
            onRegister();
        });
    }

    private void onRegister() {
        SendData sender = new SendData();
        String userType = account_selector.getValue().toString().equals("ADMIN") ? "admin" : "user";
        String response = sender.sendRegisterUserADM(name_field.getText(), email_field.getText(), DigestUtils.md5Hex(password_field.getText()).toUpperCase(), userType);
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
}
