package library_management_application.ui.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library_management_application.Action.Loader;
import library_management_application.data_model.Staff;
import library_management_application.ui.main.MainController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton cancle;

    @FXML
    private JFXButton login;

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String uname = username.getText();
        String pword = password.getText();
        Staff.validateStaff(uname, pword);
        System.out.println(Loader.auth);
        if(Loader.auth.equals("staff") || Loader.auth.equals("admin") ){
            closeStage();
            System.out.println(Loader.auth+" handleLogin");
            loadMain();

        }

    }
    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

    void loadMain() throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/library_management_application/ui/main/main" +
                    ".fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Library Assistant");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
