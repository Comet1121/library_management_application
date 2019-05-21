package library_management_application.test;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class testUiController implements Initializable {

    @FXML
    private JFXComboBox<String> comTest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comTest.getItems().add("wrweet");
        comTest.getItems().add("asdlkfjweo");
    }
}
