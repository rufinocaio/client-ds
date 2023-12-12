package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectPointCellController implements Initializable {

    @FXML
    private Label id_label;
    @FXML
    private Label name_label;
    @FXML
    private Label obs_label;
    @FXML
    private Button select_startpoint_button;
    @FXML
    private Button select_endpoint_button;

    private final Point point;

    public SelectPointCellController(Point point) {
        this.point = point;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_label.setText("ID: " + point.getID());
        name_label.setText("Nome: " + point.getName());
        String obsText = point.getObs() != null ? point.getObs() : ""; // or any default message
        obs_label.setText("Observação: " + obsText);
        select_startpoint_button.setOnAction(event -> {
            try {
                onSelectStartPoint();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        select_endpoint_button.setOnAction(event -> {
            try {
                onSelectEndPoint();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onSelectStartPoint() throws JsonProcessingException {
        Model.getInstance().getSegment().setPontoOrigem(point);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PREVIUS_MENU);
    }

    private void onSelectEndPoint() throws JsonProcessingException {
        Model.getInstance().getSegment().setPontoDestino(point);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PREVIUS_MENU);
    }
}
