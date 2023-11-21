package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendLogout;
import caiofurlan.clientdistributedsystems.system.connection.send.SendProfile;
import caiofurlan.clientdistributedsystems.system.connection.send.adminusercrud.SendClientList;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.MenuOptions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        users_button.setOnAction(event -> {
            try {
                onUsersList();
            } catch (JsonProcessingException e) {
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
        profile_button.setOnAction(event -> {
            try {
                onProfile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onRegisterUser() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.REGISTER_USER);
    }

    private void onUsersList() throws JsonProcessingException {
        SendClientList sender = new SendClientList();
        JsonNode response = sender.send(Token.getJwtToken());
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
            } else {
                receiver.getClientList();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USERS_LIST);
            }
        }
    }

    public void onLogOut() throws JsonProcessingException {
        Stage stage = (Stage) logout_button.getScene().getWindow();

        SendLogout sender = new SendLogout();
        JsonNode response = sender.send(Token.getJwtToken());
        if (response != null)
        {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
            } else {
                Token.eraseJwtToken();
                Model.getInstance().getViewFactory().showLoginWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        }
    }

    private void onProfile() throws JsonProcessingException{
        SendProfile sender = new SendProfile();
        JsonNode response = sender.send(Token.getJwtToken());
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


}
