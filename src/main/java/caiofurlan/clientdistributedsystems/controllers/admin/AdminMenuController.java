package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.AdminMenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button user_register_button;
    public Button users_button;
    public Button profile_button;
    public Button logout_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners(){
        user_register_button.setOnAction(event -> onRegisterUser ());
        users_button.setOnAction(event -> onUsersList());
        logout_button.setOnAction(event -> onLogOut());
        /* profile_button.setOnAction(event -> onProfile());
        logout_button.setOnAction(event -> onLogout()); */
    }

    private void onRegisterUser() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.REGISTER_USER);
    }

    private void onUsersList() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.USERS_LIST);
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
