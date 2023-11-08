package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.system.connection.IsValid;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendEditUserADM;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserADMController implements Initializable {
    public TextField name_field;
    public TextField email_field;
    public PasswordField password_field;
    public ChoiceBox<String> type_selector;
    public Button save_button;
    public Button left_button;
    public Label error_label;
    public Button delete_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientUserInfo(Model.getInstance().getClientUser());
        save_button.setOnAction(event -> {
            try {
                onSaveUser();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> onDelete());
        type_selector.getItems().addAll("Administrador", "Usuário");
    }

    public void setClientUserInfo(User user) {
        name_field.setText(user.getName());
        email_field.setText(user.getEmail());
        password_field.setText("");
        error_label.setText("");
        type_selector.setValue("Usuário");
    }

    private void onSaveUser() {
        SendEditUserADM sender = new SendEditUserADM();
        String name = name_field.getText();
        String password = password_field.getText();
        String email = email_field.getText();
        String type = type_selector.getValue().toString().equals("Administrador") ? "admin" : "user";
        try {
            if (IsValid.editUserIsValid(name, email, password, type)) {
                password = DigestUtils.md5Hex(password).toUpperCase();
                JsonNode response = sender.send(Token.getJwtToken(), Model.getInstance().getClientUser().getID(), name, email, password, type);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                    } else {
                        error_label.setText("Usuário editado com sucesso");
                    }
                }
            }
        } catch (Exception e) {
            Model.getInstance().getViewFactory().showErrorMessage(e.getMessage());
        }
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USERS_LIST);
    }

    private void onDelete() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.DELETE_USER_ADM);
    }
}
