package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersListController implements Initializable {
    public ListView<User> user_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_list_view.setItems(Model.getInstance().getClients());
        user_list_view.setCellFactory(studentListView -> new ClientCellFactory());
        user_list_view.refresh();
    }


}
