package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud.SendDeleteSegment;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteSegmentController implements Initializable {
    public Button left_button;
    public Button delete_button;
    public Label text_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSegmentID();
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> {
            try {
                onDelete();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void setSegmentID() {
        text_label.setText("Deseja excluir o cadastro do segmento de ID: " + Model.getInstance().getSegment().getId() + "?");
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_SEGMENT);
    }

    private void onDelete() throws Exception {
        SendDeleteSegment sender = new SendDeleteSegment();
        JsonNode response = sender.send(Model.getInstance().getSegment().getId());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SEGMENT_LIST);
                Model.getInstance().getViewFactory().showErrorWindow("Segmento excluído com sucesso");
            }
        }
    }
}
