package caiofurlan.clientdistributedsystems.controllers;

import caiofurlan.clientdistributedsystems.models.Model;
import caiofurlan.clientdistributedsystems.system.connection.ReceiveData;
import caiofurlan.clientdistributedsystems.system.connection.SendData;
import caiofurlan.clientdistributedsystems.system.utilities.Token;
import caiofurlan.clientdistributedsystems.views.AccountType;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public ChoiceBox<AccountType> account_selector;
    public TextField email_field;
    public TextField password_field;
    public Button login_button;
    public Label error_label;
    public Button register_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        email_field.setText("admin@admin.com");
        password_field.setText("123456");
        account_selector.setItems(FXCollections.observableArrayList(AccountType.values()));
        account_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        account_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(account_selector.getValue()));
        login_button.setOnAction(event -> {
            try {
                onLogin();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        register_button.setOnAction(event -> onRegister());
    }

    private void onRegister() {
        Model.getInstance().getViewFactory().closeStage((Stage) login_button.getScene().getWindow());
        Model.getInstance().getViewFactory().showRegisterWindow();
    }

    private void onLogin() throws JsonProcessingException {
        Stage stage = (Stage) error_label.getScene().getWindow();

        SendData sender = new SendData();
        String response = sender.sendLogin(email_field.getText(), DigestUtils.md5Hex(password_field.getText()).toUpperCase());
        if (response != null)
        {
            try {
                ReceiveData receiver = new ReceiveData(ReceiveData.stringToMap(response));
                if (receiver.getError()) {
                    Model.getInstance().getViewFactory().showErrorMessage(receiver.getMessage());
                } else {
                    Token.saveJwtToken(receiver.getToken());
                    if (Token.isTokenAdmin(Token.getJwtToken())) {
                        Model.getInstance().getViewFactory().showAdminWindow();
                        System.out.println("admin");
                    } else {
                        Model.getInstance().getViewFactory().showUserWindow();
                        System.out.println("user");
                    }
                    Model.getInstance().getViewFactory().closeStage(stage);
                }
            } catch (JsonProcessingException e) {
                Model.getInstance().getViewFactory().showErrorMessage(e.getMessage());
            }
        }
    }

    /* public void login()  {
        LoginData data = new LoginData(txtLogin.getText(), txtPassword.getText());

        LoginSender sender = new LoginSender(data);

        String response = sender.send();

        if (response != null) {
            try {
                LoginReceiver res = LoginReceiver.fromJson(response, LoginReceiver.class);

                if (res.getError()) {
                    HelperService.showErrorMessage(res.getMessage());
                } else {
                    TokenService.saveJwtToken(res.getData().getToken());

                    openMain();
                }

            } catch (JsonProcessingException e) {
                HelperService.showErrorMessage(e.getMessage());
            }
        }
    }

    private void openMain() {
        Stage stage = new Stage();

        String token = TokenService.getJwtToken();

        try {
            FXMLLoader loader = null;
            if (TokenService.isAdmin(token)) {
                loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
            } else {
                loader = new FXMLLoader(Main.class.getResource("client-main-menu.fxml"));
            }

            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setTitle("Main");
            stage.setScene(scene);

            Stage old_stage = (Stage) this.txtLogin.getScene().getWindow();
            old_stage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            HelperService.showErrorMessage(e.getMessage());
        }
    }*/
}
