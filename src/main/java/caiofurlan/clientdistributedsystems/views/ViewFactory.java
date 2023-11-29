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
    private AnchorPane autoEditUserView;
    private AnchorPane autoDeleteUserView;
    // User views
    private AnchorPane dashboardView;
    // Admin views
    // User CRUD
    private AnchorPane registerUserView;
    private AnchorPane usersListView;
    private AnchorPane editUserView;
    private AnchorPane deleteUserView;
    // Point CRUD
    private AnchorPane registerPointView;
    private AnchorPane pointsListView;
    private AnchorPane editPointView;
    private AnchorPane deletePointView;
    // Segment CRUD
    private AnchorPane registerSegmentView;
    private AnchorPane segmentsListView;
    private AnchorPane editSegmentView;
    private AnchorPane deleteSegmentView;
    private AnchorPane selectPointView;

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

    public ObjectProperty<MenuOptions> getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

    /* Common views */

    public void showConnectWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/connect.fxml"));
        createStage(loader, "Conecte-se!");
    }

    public void showErrorWindow(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
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

    public AnchorPane getAutoEditUserView() {
        if (autoEditUserView == null) {
            try {
                autoEditUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/autoedituser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return autoEditUserView;
    }

    public AnchorPane getAutoDeleteUserView() {
        if (autoDeleteUserView == null) {
            try {
                autoDeleteUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/common/autodeleteuser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return autoDeleteUserView;
    }

    public void showAutoRegisterUserWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/autoregisteruser.fxml"));
        createStage(loader, "Registre-se!");
    }

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

    /* User CRUD */
    public AnchorPane getRegisterUserView() {
        if (registerUserView == null) {
            try {
                registerUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercrud/registeruser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registerUserView;
    }

    public AnchorPane getUsersListView() {
        if (usersListView == null) {
            try {
                usersListView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercrud/userlist.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usersListView;
    }

    public AnchorPane getEditUserView() {
        if (editUserView == null) {
            try {
                editUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercrud/edituser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editUserView;
    }

    public AnchorPane getDeleteUserView() {
        if (deleteUserView == null) {
            try {
                deleteUserView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/usercrud/deleteuser.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteUserView;
    }

    /* Point CRUD */
    public AnchorPane getRegisterPointView() {
        if (registerPointView == null) {
            try {
                registerPointView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/pointcrud/registerpoint.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registerPointView;
    }

    public AnchorPane getPointsListView() {
        if (pointsListView == null) {
            try {
                pointsListView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/pointcrud/pointlist.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pointsListView;
    }

    public AnchorPane getEditPointView() {
        if (editPointView == null) {
            try {
                editPointView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/pointcrud/editpoint.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editPointView;
    }

    public AnchorPane getDeletePointView() {
        if (deletePointView == null) {
            try {
                deletePointView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/pointcrud/deletepoint.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletePointView;
    }

    /* Segment CRUD */

    public AnchorPane getRegisterSegmentView() {
        if (registerSegmentView == null) {
            try {
                registerSegmentView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/registersegment.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return registerSegmentView;
    }

    public AnchorPane getSegmentsListView() {
        if (segmentsListView == null) {
            try {
                segmentsListView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/segmentlist.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return segmentsListView;
    }

    public AnchorPane getEditSegmentView() {
        if (editSegmentView == null) {
            try {
                editSegmentView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/editsegment.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return editSegmentView;
    }

    public AnchorPane getDeleteSegmentView() {
        if (deleteSegmentView == null) {
            try {
                deleteSegmentView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/deletesegment.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deleteSegmentView;
    }

    public AnchorPane getSelectPointView() {
        if (selectPointView == null) {
            try {
                selectPointView = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/selectpointlist.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return selectPointView;
    }

    public void showSelectPointWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/caiofurlan/clientdistributedsystems/fxFiles/admin/segmentcrud/selectpointlist.fxml"));
        createStage(loader, "Selecione um ponto!");
    }


    public void resetAllAnchorPanes() {
        //Common views
        profileView = null;
        autoEditUserView = null;
        autoDeleteUserView = null;

        // User views
        dashboardView = null;

        // Admin views
        // User CRUD
        registerUserView = null;
        usersListView = null;
        editUserView = null;
        deleteUserView = null;
        // Point CRUD
        registerPointView = null;
        pointsListView = null;
        editPointView = null;
        deletePointView = null;
        // Segment CRUD
        registerSegmentView = null;
        segmentsListView = null;
        editSegmentView = null;
        deleteSegmentView = null;
        selectPointView = null;
    }
}
