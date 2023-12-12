package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import caiofurlan.clientdistributedsystems.views.UserCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    @FXML
    private ListView<User> user_list_view;
    @FXML
    private Button user_register_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_list_view.setItems(Model.getInstance().getUserList());
        user_list_view.setCellFactory(studentListView -> new UserCellFactory());
        user_list_view.refresh();
        user_register_button.setOnAction(event -> onRegisterUser ());
    }

    private void onRegisterUser() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_USER);
    }

}
