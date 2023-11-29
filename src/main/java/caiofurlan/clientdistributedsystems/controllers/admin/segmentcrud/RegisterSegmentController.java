package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud.SendRegisterSegment;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterSegmentController implements Initializable {
    public TextField startpoint_field;
    public TextField endpoint_field;
    public Button select_point_button;
    public TextField direction_field;
    public TextField distance_field;
    public TextField obs_field;
    public Button register_button;
    public Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setSegmentInfo();
        select_point_button.setOnAction(event -> onSelectPoint());
        register_button.setOnAction(event -> {
            try {
                onRegister();
            } catch (Exception e) {
                setError_label("Dados inválidos");
                throw new RuntimeException(e);
            }
        });
    }

    private void onSelectPoint() {
        getSegment();
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SELECT_POINT);
    }

    private void onRegister() throws Exception {
        getSegment();
        if (DataValidation.segmentInfoValidation(Model.getInstance().getSegment())) {
            SendRegisterSegment sender = new SendRegisterSegment();
            JsonNode response = sender.send(Model.getInstance().getSegment());
            if (response != null)
            {
                Receiver receiver = new Receiver(response);
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
                } else {
                    startpoint_field.setText("");
                    endpoint_field.setText("");
                    direction_field.setText("");
                    obs_field.setText("");
                    error_label.setText("Segmento cadastrado com sucesso!");
                }
            }
        }
    }

    private void setError_label(String message) {
        error_label.setText(message);
    }

    private void setSegmentInfo() {
        if (Model.getInstance().getSegment() != null) {
            if (Model.getInstance().getSegment().getPontoOrigem() != null)
                startpoint_field.setText(Model.getInstance().getSegment().getPontoOrigem().getName());
            if (Model.getInstance().getSegment().getPontoDestino() != null)
                endpoint_field.setText(Model.getInstance().getSegment().getPontoDestino().getName());
            if (Model.getInstance().getSegment().getDirecao() != null)
                direction_field.setText(Model.getInstance().getSegment().getDirecao());
            if (Model.getInstance().getSegment().getDistancia() != -1)
                distance_field.setText(String.valueOf(Model.getInstance().getSegment().getDistancia()));
            if (Model.getInstance().getSegment().getObs() != null)
                obs_field.setText(Model.getInstance().getSegment().getObs());
        }
    }

    private void getSegment() {
        Point startPoint = Model.getInstance().getPointByName(startpoint_field.getText());
        Point endPoint = Model.getInstance().getPointByName(endpoint_field.getText());
        String direction = direction_field.getText();
        int distance = -1;
        if (!distance_field.getText().equals("")) {
            distance = Integer.parseInt(distance_field.getText());
        }
        String obs = obs_field.getText().isEmpty() ? null : obs_field.getText();
        Model.getInstance().setSegment(new Segment(startPoint, endPoint, direction, distance, obs));
    }
}