package caiofurlan.clientdistributedsystems.views;

import caiofurlan.clientdistributedsystems.controllers.admin.pointcrud.PointCellController;
import caiofurlan.clientdistributedsystems.controllers.admin.segmentcrud.SegmentCellController;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.models.Segment;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class SegmentCellFactory extends ListCell<Segment> {
    @Override
    protected void updateItem(Segment segment, boolean empty) {
        super.updateItem(segment, empty);
        if(empty || segment == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/segmentcell.fxml"));
            SegmentCellController controller = new SegmentCellController(segment);
            fxmlLoader.setController(controller);
            setText(null);
            try {
                setGraphic(fxmlLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
