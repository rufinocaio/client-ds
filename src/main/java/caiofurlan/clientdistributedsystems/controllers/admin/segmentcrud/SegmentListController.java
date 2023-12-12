package caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Segment;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import caiofurlan.clientdistributedsystems.views.SegmentCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class SegmentListController implements Initializable {
    @FXML
    private Button segment_register_button;
    @FXML
    private ListView<Segment> segment_list_view;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        segment_list_view.setItems(Model.getInstance().getSegmentList());
        segment_list_view.setCellFactory(studentListView -> new SegmentCellFactory());
        segment_list_view.refresh();
        segment_register_button.setOnAction(event -> onRegisterSegment ());
    }

    private void onRegisterSegment() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_SEGMENT);
    }
}
