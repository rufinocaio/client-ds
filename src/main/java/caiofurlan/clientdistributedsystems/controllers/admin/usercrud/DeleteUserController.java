package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.usercrud.SendDeleteUser;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteUserController implements Initializable{
    @FXML
    private Button left_button;
    @FXML
    private Button delete_button;
    @FXML
    private Label text_label;

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
        text_label.setText("Deseja excluir o cadastro de: " + Model.getInstance().getUser().getName() + "?");
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_USER);
    }

    private void onDelete() throws JsonProcessingException {
        SendDeleteUser sender = new SendDeleteUser();
        JsonNode response = sender.send(Model.getInstance().getUser().getID());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USER_LIST);
                Model.getInstance().getViewFactory().showErrorWindow("Usuário excluído com sucesso");
            }
        }
    }
}
