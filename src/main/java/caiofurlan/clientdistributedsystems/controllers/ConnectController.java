package caiofurlan.clientdistributedsystems.controllers;

import caiofurlan.clientdistributedsystems.models.Model;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectController implements Initializable {
    @FXML
    private Button button_connect;

    @FXML
    private TextField tf_ip;
    @FXML
    private TextField tf_port;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tf_ip.setText("127.0.0.1");
        tf_port.setText("23000");
        button_connect.setOnAction(event -> {
            try {
                openDialog(tf_ip.getText(), tf_port.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void openDialog(String ip, String port) throws Exception {
        Platform.runLater(() -> {
            try {
                if (ip == null || ip.isEmpty()) {
                    throw new Exception("IP é obrigatório");
                }
                if (port == null || port.isEmpty()) {
                    throw new Exception("Porta é obrigatório");
                }
                Model.getInstance().getConnection().connect(ip, port);
                Model.getInstance().getViewFactory().closeStage((Stage) button_connect.getScene().getWindow());
                Model.getInstance().getViewFactory().showLoginWindow();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String getIp() {
        return this.tf_ip.getText();
    }

    public String getPort() {
        return this.tf_port.getText();
    }
}
