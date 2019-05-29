package library_management_application.ui.bookList;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library_management_application.Action.Loader;
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

    @FXML
    private JFXTextField txfSearch_box;

    @FXML
    private JFXComboBox<String> comSearch_type;

    @FXML
    private Button btnSearch;

    @FXML
    void searching(ActionEvent event) {
        searchingBookData();
    }

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

        if(Loader.auth.equals("admin")){
            tblbookList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
        comSearch_type.getItems().addAll("Book ID", "Book Title", "Author", "Category");

        try {
            tblbookList.getItems().addAll(Book.getAllList());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void searchingBookData(){
        String sql = "SELECT *" +
                "FROM books";
        String key_word = txfSearch_box.getText();

        int choice = comSearch_type.getSelectionModel().getSelectedIndex();
        System.out.println(choice);

        switch (choice){
            case 0: sql += " WHERE book_id='"+key_word+"';";break;
            case 1: sql += " WHERE book_title LIKE '%"+key_word+"%';";break;
            case 2: sql += " WHERE author_id='"+ DB_Connection.getID("author_id","author_name", key_word, "author")+"';";break;
            case 3: sql += " WHERE category_id='"+DB_Connection.getID("category_id","category_name", key_word, "category")+"';";break;
            default: break;
        }
        System.out.println(sql);
        ArrayList<Book> books = new ArrayList();


        try(Connection con = DB_Connection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while(rs.next()){
                String author_name = DB_Connection.getNameByID("author_name","author",
                            "author_id",rs.getString("author_id"));

                String supplier_name = DB_Connection.getNameByID("supplier_name", "supplier",
                        "supplier_id", rs.getString("supplier_id"));

                String category_name = DB_Connection.getNameByID("category_name", "category",
                        "category_id", rs.getString("category_id"));
                Book book = new Book(rs.getString("book_id"),
                        rs.getString("book_title"),
                        author_name,
                        supplier_name,
                        category_name,
                        rs.getInt("price"),
                        rs.getInt("qty"),
                        rs.getString("location"),
                        Book.setAvailable(rs.getInt("qty")));
                books.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        tblbookList.getItems().clear();
        tblbookList.getItems().addAll(books);

    }

}