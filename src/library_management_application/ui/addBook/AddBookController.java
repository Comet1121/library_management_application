package library_management_application.ui.addBook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import library_management_application.Action.Loader;
import library_management_application.data_model.Book;
import library_management_application.database.DB_Connection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {
    private Loader load = new Loader();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private JFXTextField txfBook_title;

    @FXML
    private JFXTextField txfPrice;

    @FXML
    private JFXTextField txfQty;

    @FXML
    private JFXTextField txfLocation;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancle;

    @FXML
    private JFXComboBox<String> comAuthor_name;

    @FXML
    private JFXComboBox<String> comSupplier_name;

    @FXML
    private JFXComboBox<String> comCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DB_Connection.loadDataForCombobox(comAuthor_name, "author_name", "author");
        DB_Connection.loadDataForCombobox(comSupplier_name, "supplier_name", "supplier");
        DB_Connection.loadDataForCombobox(comCategory, "category_name", "category");
    }


    @FXML
    void handleSave(ActionEvent event) {
        String book_title = txfBook_title.getText();
        String author_title = comAuthor_name.getValue();
        String supplier_name = comSupplier_name.getValue();
        String category = comCategory.getValue();
        int price = Integer.parseInt(txfPrice.getText());
        int qty = Integer.parseInt(txfQty.getText());
        String location = txfLocation.getText();

        Book b = new Book(DB_Connection.setID("book_id","books"),book_title, author_title, supplier_name,
                category, price,  qty, location);

        if(b.addBook()){
            new Alert(null,"Success insert").showAndWait();
        }
        else{
            new Alert(null, "something wrong").showAndWait();
        }

    }

    @FXML
    void handleCancle(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void loadAddAuthor(ActionEvent event) {
        //load.loadScene("/library_management_application/ui/");
    }

    @FXML
    void loadAddCategory(ActionEvent event) {

    }

    @FXML
    void loadAddSupplier(ActionEvent event) throws IOException {
        load.loadScene("/library_management_application/ui/addSupplier/AddSupplierUi.fxml");
    }


}
