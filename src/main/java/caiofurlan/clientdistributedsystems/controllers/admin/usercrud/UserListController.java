package caiofurlan.clientdistributedsystems.controllers.admin.usercrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.User;
import caiofurlan.clientdistributedsystems.views.UserCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserListController implements Initializable {
    public ListView<User> user_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_list_view.setItems(Model.getInstance().getUserList());
        user_list_view.setCellFactory(studentListView -> new UserCellFactory());
        user_list_view.refresh();
    }


}
