package library_management_application.ui.addMember;

import com.sun.deploy.util.FXLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMemberLoader extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddMember.fxml"));

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
