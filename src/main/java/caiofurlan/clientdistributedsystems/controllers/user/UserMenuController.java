package caiofurlan.clientdistributedsystems.controllers.user;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendLogout;
import caiofurlan.clientdistributedsystems.system.connection.send.SendProfile;
import caiofurlan.clientdistributedsystems.system.utilities.TokenManager;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public Button dashboard_button;
    public Button second_button;
    public Button third_button;
    public Button profile_button;
    public Button logout_button;
    public Button report_button;

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
        JsonNode response = sender.send(TokenManager.getToken());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
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
        Stage stage = (Stage) logout_button.getScene().getWindow();

        SendLogout sender = new SendLogout();
        JsonNode response = sender.send(TokenManager.getToken());
        if (response != null)
        {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
            } else {
                TokenManager.eraseToken();
                Model.getInstance().getViewFactory().showLoginWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        }
    }
}
