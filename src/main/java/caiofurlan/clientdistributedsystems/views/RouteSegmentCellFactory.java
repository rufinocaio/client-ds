package caiofurlan.clientdistributedsystems.views;

import caiofurlan.clientdistributedsystems.controllers.common.route.RouteSegmentCellController;
import caiofurlan.clientdistributedsystems.models.Segment;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class RouteSegmentCellFactory extends ListCell<Segment> {
    @Override
    protected void updateItem(Segment segment, boolean empty) {
        super.updateItem(segment, empty);
        if(empty || segment == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/route/routesegmentcell.fxml"));
            int index = getIndex();
            RouteSegmentCellController controller = new RouteSegmentCellController(segment, index);
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
