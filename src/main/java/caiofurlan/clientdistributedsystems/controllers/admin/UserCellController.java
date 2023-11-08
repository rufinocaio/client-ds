package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCellController implements Initializable {

    public Label id_label;
    public Label name_label;
    public Label type_label;
    public Label email_label;
    public Button edit_button;

    private final User client;

    public UserCellController(User client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_label.setText("ID: " + String.valueOf(client.getID()));
        name_label.setText("Nome: " + client.getName());
        type_label.setText("Tipo: " + (client.getType().equals("admin") ? "Administrador" : "Usuário"));
        email_label.setText("Email: " + client.getEmail());
        edit_button.setOnAction(event -> {
            try {
                onEditUser();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onEditUser() throws JsonProcessingException {
        Model.getInstance().setClientUser(client);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.EDIT_USER_ADM);
    }
}
