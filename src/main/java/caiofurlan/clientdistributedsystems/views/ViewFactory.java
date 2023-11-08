package caiofurlan.clientdistributedsystems.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

public class ViewFactory {

    private final ObjectProperty<MenuOptions> selectedMenuItem;

    //Common views
    private AnchorPane profileView;
    private AnchorPane editUserView;
    private AnchorPane deleteUserView;

    // Client views
    private AnchorPane dashboardView;
    /* ----------------- */

    // Admin views
    private AnchorPane registerUserView;
    private AnchorPane usersListView;
    private AnchorPane editUserADMView;
    private AnchorPane deleteUserADMView;
    /* ----------------- */

    public ViewFactory() {
        this.selectedMenuItem = new SimpleObjectProperty<>();
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
    public ObjectProperty<MenuOptions> getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/login.fxml"));
        createStage(loader, "Log in!");
    }

    public AnchorPane getProfileView() {
        if (profileView == null) {
            try {
                profileView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/profile.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileView;
    }

    public AnchorPane getEditUserView() {
        if (editUserView == null) {
            try {
                editUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/edituser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editUserView;
    }

    public AnchorPane getDeleteUserView() {
        if (deleteUserView == null) {
            try {
                deleteUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/deleteuser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteUserView;
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

    public AnchorPane getEditUserAdmView() {
        if (editUserADMView == null) {
            try {
                editUserADMView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/edituseradm.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editUserADMView;
    }

    public AnchorPane getDeleteUserAdmView() {
        if (deleteUserADMView == null) {
            try {
                deleteUserADMView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/deleteuseradm.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteUserADMView;
    }

    public void resetAllAnchorPanes() {
        profileView = null;
        editUserView = null;
        deleteUserView = null;
        registerUserView = null;
        usersListView = null;
        editUserADMView = null;
        deleteUserADMView = null;
    }

    /* ----------------- */

}
