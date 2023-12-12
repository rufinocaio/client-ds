package caiofurlan.clientdistributedsystems.controllers.user;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendLogout;
import caiofurlan.clientdistributedsystems.system.connection.send.SendProfile;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    @FXML
    private Button dashboard_button;
    @FXML
    private Button second_button;
    @FXML
    private Button third_button;
    @FXML
    private Button profile_button;
    @FXML
    private Button logout_button;
    @FXML
    private Button report_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        dashboard_button.setOnAction(event -> onDashboard());
        profile_button.setOnAction(event -> {
            try {
                onProfile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        logout_button.setOnAction(event -> {
            try {
                onLogOut();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onProfile() throws Exception{
        SendProfile sender = new SendProfile();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                receiver.getUser();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.PROFILE);
            }
        }
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.DASHBOARD);
    }

    public void onLogOut() throws JsonProcessingException {
        SendLogout sender = new SendLogout();
        JsonNode response = sender.send();
        if (response != null)
        {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                TokenManager.eraseToken();
                Model.getInstance().getViewFactory().showLoginWindow();
                Model.getInstance().getViewFactory().closeStage((Stage) logout_button.getScene().getWindow());
            }
        }
    }
}
