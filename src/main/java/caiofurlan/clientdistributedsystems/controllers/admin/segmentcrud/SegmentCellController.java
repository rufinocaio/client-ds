package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;

import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SegmentCellController implements Initializable {

    public Label startpoint_id_label;
    public Label startpoint_name_label;
    public Label startpoint_obs_label;

    public Label endpoint_id_label;
    public Label endpoint_name_label;
    public Label endpoint_obs_label;

    public Label id_label;
    public Label direction_label;
    public Label distance_label;
    public Label obs_label;
    public Button edit_button;

    private final Segment segment;

    public SegmentCellController(Segment segment) {
        this.segment = segment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startpoint_id_label.setText("ID: " + segment.getPontoOrigem().getID());
        startpoint_name_label.setText("Nome: " + segment.getPontoOrigem().getName());
        startpoint_obs_label.setText("Observação: " + segment.getPontoOrigem().getObs());
        endpoint_id_label.setText("ID: " + segment.getPontoDestino().getID());
        endpoint_name_label.setText("Nome: " + segment.getPontoDestino().getName());
        endpoint_obs_label.setText("Observação: " + segment.getPontoDestino().getObs());
        id_label.setText("ID: " + segment.getId());
        direction_label.setText("Direção: " + segment.getDirecao());
        distance_label.setText("Distância: " + segment.getDistancia());
        obs_label.setText("Observação: " + segment.getObs());
        edit_button.setOnAction(event -> {
            try {
                onEditSegment();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onEditSegment() throws JsonProcessingException {
        Model.getInstance().setSegment(segment);
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.EDIT_SEGMENT);
    }

}
