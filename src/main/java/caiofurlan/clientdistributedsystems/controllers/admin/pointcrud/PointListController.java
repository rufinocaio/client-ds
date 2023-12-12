package caiofurlan.clientdistributedsystems.controllers.admin.pointcrud;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.models.Point;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import caiofurlan.clientdistributedsystems.views.PointCellFactory;
import caiofurlan.clientdistributedsystems.views.UserCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PointListController implements Initializable {
    @FXML
    private ListView<Point> point_list_view;
    @FXML
    private Button point_register_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        point_list_view.setItems(Model.getInstance().getPointList());
        point_list_view.setCellFactory(studentListView -> new PointCellFactory());
        point_list_view.refresh();
        point_register_button.setOnAction(event -> onRegisterPoint ());
    }

    private void onRegisterPoint() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_POINT);
    }
}
