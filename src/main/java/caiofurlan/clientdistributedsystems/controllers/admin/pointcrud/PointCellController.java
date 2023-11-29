package caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PointCellController implements Initializable {

    public Label id_label;
    public Label name_label;
    public Label obs_label;
    public Button edit_button;

    private final Point point;

    public PointCellController(Point point) {
        this.point = point;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_label.setText("ID: " + point.getID());
        name_label.setText("Nome: " + point.getName());
        obs_label.setText("Observação: " + point.getObs());
        edit_button.setOnAction(event -> {
            try {
                onEditPoint();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onEditPoint() throws JsonProcessingException {
        Model.getInstance().setPoint(point);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.EDIT_POINT);
    }
}
