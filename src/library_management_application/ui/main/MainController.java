package library_management_application.ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import library_management_application.Action.Loader;
import library_management_application.database.DB_Connection;
import library_management_application.ui.login.LoginController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static Loader load = new Loader();
    @FXML
    private JFXComboBox<String> comBook_id;
    @FXML
    private JFXComboBox<String> comBook_title;

    @FXML
    private JFXComboBox<String> comAuthor_name;

    @FXML
    private JFXComboBox<String> comMember_id;

    @FXML
    private JFXComboBox<String> comMember_name;

    @FXML
    private JFXDatePicker txfIssue_date;

    @FXML
    private JFXDatePicker txfReturn_date;

    @FXML
    private TableView<?> tblIssueRecord;

    @FXML
    private TableColumn<?, ?> member_Name;

    @FXML
    private TableColumn<?, ?> book_Title;

    @FXML
    private TableColumn<?, ?> author_Name;

    @FXML
    private Label availbility;

    @FXML
    private JFXButton add;

    @FXML
    private JFXButton remove;

    @FXML
    private JFXButton save;

    @FXML
    private Label totalPrice;

    @FXML
    private JFXComboBox<?> book_id;

    @FXML
    private VBox vbox1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DB_Connection.loadDataForCombobox(comBook_id, "book_id", "books");
        DB_Connection.loadDataForCombobox(comBook_title, "book_title", "books");
        DB_Connection.loadDataForCombobox(comAuthor_name, "author_name", "author");
        DB_Connection.loadDataForCombobox(comMember_name, "member_name", "member");

        if (Loader.auth.equals("admin")) {
            JFXButton btnAddBook = new JFXButton("Add Book");
            btnAddBook.setId("vbox-button");

            JFXButton btnAddMember = new JFXButton("Add Member");
            btnAddMember.setId("vbox-button");

            vbox1.getChildren().add(1, btnAddBook);
            vbox1.getChildren().add(2, btnAddMember);
            btnAddBook.setOnAction(ActionEvent -> {
                try {
                    load.loadScene("/library_management_application/ui/addBook/AddBook.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            btnAddMember.setOnAction(ActionEvent -> {
                try {
                    load.loadScene("/library_management_application/ui/addMember/AddMember.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    void calculateReturnDate(ActionEvent event) {
        System.out.println("call calculation");
        LocalDate issue_date = txfIssue_date.getValue();
        LocalDate return_date = issue_date.plusDays(14);
        txfReturn_date.setValue(return_date);
    }

    @FXML
    void addingIssue(ActionEvent event) {

    }

    @FXML
    void removeIssueRecord(ActionEvent event) {

    }

    @FXML
    void addIssueRecord(ActionEvent event) {

    }

    @FXML
    void loadBookData(ActionEvent event) {
        System.out.println("loadBookData");
        String sql = "SELECT author_id, book_title FROM books WHERE books.book_id=?";
        try(Connection conn = DB_Connection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,comBook_id.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("reach rs");
                comBook_title.setValue(rs.getString("book_title"));
                System.out.println(rs.getString("author_id"));
                comAuthor_name.setValue(DB_Connection.getNameByID("author_name","author","author_id", rs.getString("author_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

