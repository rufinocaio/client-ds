package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.usercrud.SendEditUser;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private TextField email_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private ChoiceBox<String> type_selector;
    @FXML
    private Button save_button;
    @FXML
    private Button left_button;
    @FXML
    private Label error_label;
    @FXML
    private Button delete_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientUserInfo(Model.getInstance().getUser());
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
        String name = name_field.getText();
        String password = password_field.getText();
        String email = email_field.getText();
        String type = type_selector.getValue().toString().equals("Administrador") ? "admin" : "user";
        try {
            if (DataValidation.userInfoValidation(name, email, password, type)) {
                password = DigestUtils.md5Hex(password).toUpperCase();
                SendEditUser sender = new SendEditUser();
                JsonNode response = sender.send(Model.getInstance().getUser().getID(), name, email, password, type);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                    } else {
                        error_label.setText("Usuário editado com sucesso");
                    }
                }
            }
        } catch (Exception e) {
            Model.getInstance().getViewFactory().showErrorWindow(e.getMessage());
        }
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USER_LIST);
    }

    private void onDelete() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.DELETE_USER);
    }
}
