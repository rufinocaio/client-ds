package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SegmentCellController implements Initializable {

    @FXML
    private Label startpoint_id_label;
    @FXML
    private Label startpoint_name_label;
    @FXML
    private Label startpoint_obs_label;

    @FXML
    private Label endpoint_id_label;
    @FXML
    private Label endpoint_name_label;
    @FXML
    private Label endpoint_obs_label;

    @FXML
    private Label id_label;
    @FXML
    private Label direction_label;
    @FXML
    private Label distance_label;
    @FXML
    private Label blocked_label;
    @FXML
    private Label obs_label;
    @FXML
    private Button edit_button;

    private final Segment segment;

    public SegmentCellController(Segment segment) {
        this.segment = segment;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startpoint_id_label.setText("ID: " + segment.getPontoOrigem().getID());
        startpoint_name_label.setText("Nome: " + segment.getPontoOrigem().getName());
        String startPointObsText = segment.getPontoOrigem().getObs() != null ? segment.getPontoOrigem().getObs() : "";
        startpoint_obs_label.setText("Observação: " + startPointObsText);

        endpoint_id_label.setText("ID: " + segment.getPontoDestino().getID());
        endpoint_name_label.setText("Nome: " + segment.getPontoDestino().getName());
        String endPointObsText = segment.getPontoDestino().getObs() != null ? segment.getPontoDestino().getObs() : "";
        endpoint_obs_label.setText("Observação: " + endPointObsText);

        id_label.setText("ID: " + segment.getId());
        direction_label.setText("Direção: " + segment.getDirecao());
        distance_label.setText("Distância: " + segment.getDistancia());
        String obsText = segment.getObs() != null ? segment.getObs() : "";
        String blocked = segment.getBloqueado() ? "Sim" : "Não";
        blocked_label.setText("Bloqueado: " + blocked);
        obs_label.setText("Observação: " + obsText);

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
