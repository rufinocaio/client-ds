package caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.pointcrud.SendDeletePoint;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeletePointController implements Initializable {
    @FXML
    private Button left_button;
    @FXML
    private Button delete_button;
    @FXML
    private Label text_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPointName();
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> {
            try {
                onDelete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setPointName() {
        text_label.setText("Deseja excluir o cadastro do ponto " + Model.getInstance().getPoint().getName() + "?");
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_POINT);
    }

    private void onDelete() throws Exception {
        SendDeletePoint sender = new SendDeletePoint();
        JsonNode response = sender.send(Model.getInstance().getPoint().getID());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.POINT_LIST);
                Model.getInstance().getViewFactory().showErrorWindow("Ponto excluído com sucesso");
            }
        }
    }
}
