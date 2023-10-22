package caiofurlan.clientdistributedsystems.views;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewFactory {
    private AccountType loginAccountType;

    // Client views
    private final ObjectProperty<UserMenuOptions> userSelectedMenuItem;
    private AnchorPane dashboardView;
    /* ----------------- */

    // Admin views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane registerUserView;
    private AnchorPane usersListView;
    /* ----------------- */

    public ViewFactory() {
        this.loginAccountType = AccountType.USER;
        this.userSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }
    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    private void createStage(FXMLLoader loader, String title) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
    }

    /* Common views */
    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/login.fxml"));
        createStage(loader, "Log in!");
    }

    public void showConnectWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/connect.fxml"));
        createStage(loader, "Conecte-se!");
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void showRegisterWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/registeruser.fxml"));
        createStage(loader, "Registre-se!");
    }
    /* ----------------- */

    /* User views */
    public ObjectProperty<UserMenuOptions> getUserSelectedMenuItem() {
        return userSelectedMenuItem;
    }

    public void showUserWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/user/user.fxml"));
        createStage(loader, "Bem-vindo!");
    }

    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/user/dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardView;
    }
    /* ----------------- */

    /* Admin views */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/admin.fxml"));
        createStage(loader, "Bem-vindo!");
    }

    public AnchorPane getRegisterUserView() {
        if (registerUserView == null) {
            try {
                registerUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/registeruseradm.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registerUserView;
    }

    public AnchorPane getUsersListView() {
        if (usersListView == null) {
            try {
                usersListView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/userslist.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usersListView;
    }

    /* ----------------- */

}
