package library_management_application.ui.addMember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import library_management_application.data_model.Member;
import library_management_application.database.DB_Connection;
import java.text.SimpleDateFormat;
import java.sql.*;

public class AddMemberController {

    @FXML
    private JFXTextField txfMember_name;

    @FXML
    private JFXTextField txfPhone;

    @FXML
    private JFXDatePicker txfStartDate;

    @FXML
    private JFXDatePicker txfExpireDate;

    @FXML
    private JFXTextField txfNRC;

    @FXML
    private JFXTextArea txfAddress;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancle;

    @FXML
    void calculateExpireDate(ActionEvent event) {

    }

    @FXML
    void handleCancle(ActionEvent event) {

    }

    @FXML
    void handleSave(ActionEvent event) {
        try {
            String member_id = DB_Connection.setID("member_id","member");
            String member_name = txfMember_name.getText();
            String NRC = txfNRC.getText();
            String address = txfAddress.getText();
            String phone = txfPhone.getText();

            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date sdate = sdf.parse(txfStartDate.getEditor().getText());
            long msd = sdate.getTime();

            java.util.Date edate = sdf.parse(txfExpireDate.getEditor().getText());
            long med = edate.getTime();

            Date member_start_date = new Date(msd);
            Date member_expire_date = new Date(med);

            Member m = new Member(member_id, member_name, NRC, address, phone,member_start_date,member_expire_date);
            if(m.addMember()){
                new Alert(Alert.AlertType.INFORMATION,"success Insert", ButtonType.OK).showAndWait();
                clearField();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"something wrong", ButtonType.OK).showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void clearField(){
        txfMember_name.clear();
        txfNRC.clear();
        txfAddress.clear();
        txfPhone.clear();
        txfStartDate.setValue(null);
        txfExpireDate.setValue(null);
    }

}
