package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class UserCellController implements Initializable {

    @FXML
    private Label id_label;
    @FXML
    private Label name_label;
    @FXML
    private Label type_label;
    @FXML
    private Label email_label;
    @FXML
    private Button edit_button;

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
        Model.getInstance().setUser(client);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.EDIT_USER);
    }
}
