package caiofurlan.clientdistributedsystems.views;

import caiofurlan.clientdistributedsystems.controllers.admin.usercrud.UserCellController;
import caiofurlan.clientdistributedsystems.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class UserCellFactory extends ListCell<User> {
    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if(empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercrud/usercell.fxml"));
            UserCellController controller = new UserCellController(user);
            fxmlLoader.setController(controller);
            setText(null);
            try {
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
