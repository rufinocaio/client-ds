package caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.pointcrud.SendEditPoint;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditPointController implements Initializable {
    public TextField name_field;
    public TextField obs_field;
    public Button save_button;
    public Button delete_button;
    public Button left_button;
    public Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPointInfo(Model.getInstance().getPoint());
        save_button.setOnAction(event -> {
            try {
                onSavePoint();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> onDelete());
    }

    public void setPointInfo(Point point) {
        name_field.setText(point.getName());
        obs_field.setText(point.getObs());
        error_label.setText("");
    }

    private void onSavePoint() {
        String name = name_field.getText();
        String obs = obs_field.getText().isEmpty() ? null : obs_field.getText();
        try {
            if (DataValidation.pointInfoValidation(name)) {
                SendEditPoint sender = new SendEditPoint();
                JsonNode response = sender.send(Model.getInstance().getPoint().getID(), name, obs);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                    } else {
                        error_label.setText("Ponto editado com sucesso");
                    }
                }
            }
        } catch (Exception e) {
            Model.getInstance().getViewFactory().showErrorWindow(e.getMessage());
        }
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.POINT_LIST);
    }

    private void onDelete() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.DELETE_POINT);
    }
}
