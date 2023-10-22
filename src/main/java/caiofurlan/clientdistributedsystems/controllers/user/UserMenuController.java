package caiofurlan.clientdistributedsystems.controllers.user;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.UserMenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
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
        logout_button.setOnAction(event -> onLogOut());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(UserMenuOptions.DASHBOARD);
    }

    public void onLogOut() {
        Stage stage = (Stage) logout_button.getScene().getWindow();

        SendData sender = new SendData();
        String response = sender.sendLogout(Token.getJwtToken());
        if (response != null)
        {
            try {
                ReceiveData receiver = new ReceiveData(ReceiveData.stringToMap(response));
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                } else {
                    Token.eraseJwtToken();
                    Model.getInstance().getViewFactory().showLoginWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);
                }
            } catch (JsonProcessingException e) {
                Model.getInstance().getViewFactory().showErrorMessage(e.getMessage());
            }
        }
    }
}
