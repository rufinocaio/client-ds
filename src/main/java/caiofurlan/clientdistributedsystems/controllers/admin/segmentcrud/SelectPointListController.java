package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.views.SelectPointCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectPointListController implements Initializable {
    @FXML
    private ListView<Point> point_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        point_list_view.setItems(Model.getInstance().getPointList());
        point_list_view.setCellFactory(studentListView -> new SelectPointCellFactory());
        point_list_view.refresh();
    }
}
