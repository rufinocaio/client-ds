package caiofurlan.clientdistributedsystems.views;

import caiofurlan.clientdistributedsystems.controllers.admin.UserCellController;
import caiofurlan.clientdistributedsystems.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ClientCellFactory extends ListCell<User> {
    @Override
    protected void updateItem(User client, boolean empty) {
        super.updateItem(client, empty);
        if(empty || client == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercell.fxml"));
            UserCellController controller = new UserCellController(client);
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
