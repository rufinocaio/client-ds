package caiofurlan.clientdistributedsystems.controllers.common;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.autousercrud.SendAutoDeleteUser;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AutoDeleteUserController implements Initializable {
    public Button left_button;
    public Button delete_button;
    public PasswordField password_field;
    public Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> onDelete());
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PROFILE);
    }

    private void onDelete() {
        SendAutoDeleteUser sender = new SendAutoDeleteUser();
        String password = password_field.getText();
        try {
            if (DataValidation.isPasswordValid(password)) {
                password = DigestUtils.md5Hex(password).toUpperCase();
                JsonNode response = sender.send(Model.getInstance().getSelfUser().getEmail(), password);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                    } else {
                        Stage stage = (Stage) error_label.getScene().getWindow();
                        Model.getInstance().getViewFactory().showLoginWindow();
                        Model.getInstance().getViewFactory().closeStage(stage);
                        Model.getInstance().getViewFactory().showErrorWindow("Usuário excluído com sucesso");
                    }
                }
            }
        } catch (Exception e) {
            setError_label("Dados inválidos");
            throw new RuntimeException(e);
        }

    }

    private void setError_label(String message) {
        error_label.setText(message);
    }

}
