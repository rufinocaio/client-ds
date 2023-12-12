package caiofurlan.clientdistributedsystems.controllers.admin;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.receive.Receiver;
import caiofurlan.clientdistributedsystems.system.connection.send.SendLogout;
import caiofurlan.clientdistributedsystems.system.connection.send.SendProfile;
import caiofurlan.clientdistributedsystems.system.connection.send.pointcrud.SendPointList;
import caiofurlan.clientdistributedsystems.system.connection.send.segmentcrud.SendSegmentList;
import caiofurlan.clientdistributedsystems.system.connection.send.usercrud.SendUserList;
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

public class AdminMenuController implements Initializable {
    @FXML
    private Button users_button;
    @FXML
    private Button points_button;
    @FXML
    private Button segments_button;
    @FXML
    private Button profile_button;
    @FXML
    private Button logout_button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners(){
        users_button.setOnAction(event -> {
            try {
                onUserList();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        points_button.setOnAction(event -> {
            try {
                onPointList();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        segments_button.setOnAction(event -> {
            try {
                onSegmentList();
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

    private void onUserList() throws JsonProcessingException {
        SendUserList sender = new SendUserList();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                receiver.getUserList();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.USER_LIST);
            }
        }
    }

    private void onPointList() throws JsonProcessingException {
        SendPointList sender = new SendPointList();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                receiver.getPointList();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.POINT_LIST);
            }
        }
    }

    private void onSegmentList() throws JsonProcessingException {
        SendSegmentList sender = new SendSegmentList();
        JsonNode response = sender.send();
        if (response != null) {
            Receiver receiver = new Receiver(response);
            if (receiver.getError()) {
                Model.getInstance().getViewFactory().showErrorWindow(receiver.getMessage());
            } else {
                receiver.getSegmentList();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MenuOptions.SEGMENT_LIST);
            }
        }
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

    private void onProfile() throws JsonProcessingException{
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


}
