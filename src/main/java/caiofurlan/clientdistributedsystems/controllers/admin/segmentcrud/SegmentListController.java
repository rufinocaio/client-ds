package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.views.SegmentCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SegmentListController implements Initializable {
    public ListView<Segment> segment_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        segment_list_view.setItems(Model.getInstance().getSegmentList());
        segment_list_view.setCellFactory(studentListView -> new SegmentCellFactory());
        segment_list_view.refresh();
    }
}
