package library_management_application.ui.bookList;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library_management_application.data_model.Book;
import library_management_application.database.DB_Connection;

public class BookListController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Book> tblbookList;

    @FXML
    private TableColumn<Book, String> book_idCol;

    @FXML
    private TableColumn<Book, String> book_titleCol;

    @FXML
    private TableColumn<Book, String> author_nameCol;

    @FXML
    private TableColumn<Book, String> category_nameCol;

    @FXML
    private TableColumn<Book, Integer> priceCol;

    @FXML
    private TableColumn<Book, Integer> qtyCol;

    @FXML
    private TableColumn<Book, String> availableCol;

    @FXML
    private TableColumn<Book, String> locationCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        book_idCol.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        book_idCol.setSortable(true);
        book_titleCol.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        author_nameCol.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        category_nameCol.setCellValueFactory(new PropertyValueFactory<>("category_name"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        availableCol.setCellValueFactory(new PropertyValueFactory<>("available"));


        try {
            tblbookList.getItems().addAll(Book.getAllList());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}