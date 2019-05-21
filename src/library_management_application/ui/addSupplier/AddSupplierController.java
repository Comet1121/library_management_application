package library_management_application.ui.addSupplier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import library_management_application.data_model.Supplier;
import library_management_application.database.DB_Connection;

public class AddSupplierController {

    @FXML
    private JFXTextField txfSupplier_name;

    @FXML
    private JFXTextField txfPhone;

    @FXML
    private JFXTextArea txfAddress;

    @FXML
    private JFXButton btnCancle;

    @FXML
    void cancle(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        String name = txfSupplier_name.getText();
        String phone = txfPhone.getText();
        String address = txfAddress.getText();
        String id = DB_Connection.setID("supplier_id", "supplier");

        Supplier sp = new Supplier(DB_Connection.setID("supplier_id", "supplier"), name, phone, address);
        if (sp.addSupplier()){
            new Alert(Alert.AlertType.INFORMATION, "success Insert!", ButtonType.OK).showAndWait();
        }
        else {
            new Alert(Alert.AlertType.INFORMATION, "not success!", ButtonType.OK).showAndWait();
        }
    }

}
