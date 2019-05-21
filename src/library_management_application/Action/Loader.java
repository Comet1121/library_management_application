package library_management_application.Action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Loader {
    public static String auth;

    public void loadScene(String source) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(source));

        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();
    }
}
