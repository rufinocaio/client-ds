package caiofurlan.clientdistributedsystems.controllers.common.route;

import caiofurlan.clientdistributedsystems.models.Segment;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteSegmentCellController implements Initializable {

    @FXML
    private Label segment_id_label;
    @FXML
    private Label startpoint_name_label;
    @FXML
    private Label startpoint_obs_label;


    @FXML
    private Label endpoint_name_label;
    @FXML
    private Label endpoint_obs_label;

    @FXML
    private Label direction_label;
    @FXML
    private Label distance_label;
    @FXML
    private Label obs_label;

    private final Segment segment;
    private final int index;

    public RouteSegmentCellController(Segment segment, int index) {
        this.segment = segment;
        this.index = index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        segment_id_label.setText("" + (index + 1));
        startpoint_name_label.setText("Nome: " + segment.getPontoOrigem().getName());
        String startPointObsText = segment.getPontoOrigem().getObs() != null ? segment.getPontoOrigem().getObs() : "";
        startpoint_obs_label.setText("Observação: " + startPointObsText);

        endpoint_name_label.setText("Nome: " + segment.getPontoDestino().getName());
        String endPointObsText = segment.getPontoDestino().getObs() != null ? segment.getPontoDestino().getObs() : "";
        endpoint_obs_label.setText("Observação: " + endPointObsText);

        direction_label.setText("Direção: " + segment.getDirecao());
        distance_label.setText("Distância: " + segment.getDistancia());
        String obsText = segment.getObs() != null ? segment.getObs() : "";
        obs_label.setText("Observação: " + obsText);
    }
}
