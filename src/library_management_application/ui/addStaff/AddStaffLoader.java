package library_management_application.ui.addStaff;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddStaffLoader extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/library_management_application/ui/addStaff/" +
                "AddStaffUi.fxml"));

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
