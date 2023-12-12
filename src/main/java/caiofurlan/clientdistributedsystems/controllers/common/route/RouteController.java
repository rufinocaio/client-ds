package caiofurlan.clientdistributedsystems.controllers.common.route;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.pointcrud.SendPointList;
import caiofurlan.clientdistributedsystems.system.connection.send.routes.SendRequestRoute;
import caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud.SendRegisterSegment;
import caiofurlan.clientdistributedsystems.system.utilities.DataValidation;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import caiofurlan.clientdistributedsystems.views.RouteSegmentCellFactory;
import caiofurlan.clientdistributedsystems.views.SegmentCellFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RouteController implements Initializable {

    @FXML
    private ListView<Segment> route_list_view;
    @FXML
    private TextField startpoint_field;
    @FXML
    private TextField endpoint_field;
    @FXML
    private Button select_point_button;
    @FXML
    private Button search_button;
    @FXML
    private Label error_label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            onPointList();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        startpoint_field.setEditable(false);
        endpoint_field.setEditable(false);
        error_label.setText("");
        setRouteInfo();
        select_point_button.setOnAction(event -> {
            try {
                onSelectPoint();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        search_button.setOnAction(event -> {
            try {
                onSearch();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onPointList() throws JsonProcessingException {
        SendPointList sender = new SendPointList();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                receiver.getPointList();
            }
        }
    }

    private void onSearch() throws Exception {
        getRoute();
        if (DataValidation.routeInfoValidation(startpoint_field.getText(), endpoint_field.getText())) {
            SendRequestRoute sender = new SendRequestRoute();
            Point[] points = new Point[2];
            points[0] = Model.getInstance().getPointByName(startpoint_field.getText());
            points[1] = Model.getInstance().getPointByName(endpoint_field.getText());
            JsonNode response = sender.send(points);
            if (response != null)
            {
                Receiver receiver = new Receiver(response);
                if (receiver.getError()) {
                    error_label.setText(receiver.getMessage());
                } else {
                    receiver.getRouteList();
                    startpoint_field.setText("");
                    endpoint_field.setText("");
                    error_label.setText("Rota resgatada com sucesso!");
                    route_list_view.setItems(Model.getInstance().getRouteList());
                    route_list_view.setCellFactory(studentListView -> new RouteSegmentCellFactory());
                    route_list_view.refresh();
                }
            }
        }
    }

    private void onSelectPoint() {
        getRoute();
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SELECT_POINT);
    }

    private void setRouteInfo() {
        if (Model.getInstance().getSegment() != null) {
            if (Model.getInstance().getSegment().getPontoOrigem() != null)
                startpoint_field.setText(Model.getInstance().getSegment().getPontoOrigem().getName());
            if (Model.getInstance().getSegment().getPontoDestino() != null)
                endpoint_field.setText(Model.getInstance().getSegment().getPontoDestino().getName());
        }
    }

    private void getRoute() {
        Point startPoint = Model.getInstance().getPointByName(startpoint_field.getText());
        Point endPoint = Model.getInstance().getPointByName(endpoint_field.getText());
        Model.getInstance().setSegment(new Segment(startPoint, endPoint, null, 0, false, null));
    }
}
