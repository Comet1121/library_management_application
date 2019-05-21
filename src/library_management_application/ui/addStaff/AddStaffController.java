package library_management_application.ui.addStaff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import library_management_application.data_model.Staff;
import library_management_application.database.DB_Connection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddStaffController implements Initializable {

    @FXML
    private JFXTextField txfName;

    @FXML
    private JFXTextField txfPhone;

    @FXML
    private JFXTextArea txfAddress;

    @FXML
    private JFXPasswordField txfPassword;

    @FXML
    private JFXComboBox<String> comRole;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCanle;

    @FXML
    void cancle(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        String name = txfName.getText();
        String password = txfPassword.getText();
        String phone = txfPhone.getText();
        String address = txfAddress.getText();
        String role_type = comRole.getValue();

        Staff staff = new Staff(DB_Connection.setID("staff_id", "staff"), name, password, role_type, phone, address);

        if(staff.addStaff()){
            new Alert(Alert.AlertType.INFORMATION, "Insert Success!", ButtonType.OK).showAndWait();
        }
        else{
            new Alert(Alert.AlertType.INFORMATION, "not Success!", ButtonType.OK).showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DB_Connection.loadDataForCombobox(comRole,"role_type", "role");
    }
}