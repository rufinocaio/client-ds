package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.adminusercrud.SendDeleteUserADM;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserADMController implements Initializable{
    public Button left_button;
    public Button delete_button;
    public Label text_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setClientName();
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> {
            try {
                onDelete();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setClientName() {
        text_label.setText("Deseja excluir o cadastro de: " + Model.getInstance().getClientUser().getName() + "?");
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_USER);
    }

    private void onDelete() throws JsonProcessingException {
        SendDeleteUserADM sender = new SendDeleteUserADM();
        JsonNode response = sender.send(Token.getJwtToken(), Model.getInstance().getClientUser().getID());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
            } else {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USERS_LIST);
                Model.getInstance().getViewFactory().showErrorMessage("Usuário excluído com sucesso");
            }
        }
    }
}
