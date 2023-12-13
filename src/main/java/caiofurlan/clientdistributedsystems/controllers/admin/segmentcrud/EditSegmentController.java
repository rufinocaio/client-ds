package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud.SendEditSegment;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSegmentController implements Initializable {

    @FXML
    private TextField startpoint_field;
    @FXML
    private TextField endpoint_field;
    @FXML
    private Button select_point_button;
    @FXML
    private TextField direction_field;
    @FXML
    private TextField distance_field;
    @FXML
    private CheckBox blocked_checkbox;
    @FXML
    private TextField obs_field;
    @FXML
    private Button save_button;
    @FXML
    private Button delete_button;
    @FXML
    private Button left_button;
    @FXML
    private Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startpoint_field.setEditable(false);
        endpoint_field.setEditable(false);
        setSegmentInfo();
        select_point_button.setOnAction(event -> onSelectPoint());
        save_button.setOnAction(event -> {
            try {
                onSaveSegment();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        left_button.setOnAction(event -> onLeft());
        delete_button.setOnAction(event -> onDelete());
    }

    private void onSelectPoint() {
        getSegment();
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SELECT_POINT);
    }

    private void onSaveSegment() {
        Segment segment = getSegment();
        try {
            if (DataValidation.segmentInfoValidation(segment)) {
                SendEditSegment sender = new SendEditSegment();
                JsonNode response = sender.send(Model.getInstance().getSegment().getId(), segment);
                if (response != null) {
                    Receiver receiver = new Receiver(response);
                    if (receiver.getError()) {
                        Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                    } else {
                        error_label.setText("Segmento editado com sucesso");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onLeft() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SEGMENT_LIST);
    }

    private void onDelete() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.DELETE_SEGMENT);
    }

    public void setSegmentInfo() {
        startpoint_field.setText(Model.getInstance().getSegment().getPontoOrigem().getName());
        endpoint_field.setText(Model.getInstance().getSegment().getPontoDestino().getName());
        direction_field.setText(Model.getInstance().getSegment().getDirecao());
        distance_field.setText(String.valueOf(Model.getInstance().getSegment().getDistancia()));
        blocked_checkbox.setSelected(Model.getInstance().getSegment().getBloqueado());
        obs_field.setText(Model.getInstance().getSegment().getObs());
        error_label.setText("");
    }

    private Segment getSegment() {
        Point startPoint = Model.getInstance().getPointByName(startpoint_field.getText());
        Point endPoint = Model.getInstance().getPointByName(endpoint_field.getText());
        String direction = direction_field.getText();
        int distance = Integer.parseInt(distance_field.getText());
        boolean blocked = blocked_checkbox.isSelected();
        String obs = obs_field.getText();
        if (obs != null && obs.isEmpty()) {
            obs = null;
        }
        return new Segment(startPoint, endPoint, direction, distance, blocked, obs);
    }
}
