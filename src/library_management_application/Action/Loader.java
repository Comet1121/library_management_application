package library_management_application.Action;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library_management_application.database.DB_Connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Loader {
    public static String auth;

    public void loadScene(String source) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(source));

        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();
    }

    public void loadBookData(String load_type1, String col_type, JFXComboBox<String> load_com1, JFXComboBox<String> com_author, JFXComboBox<String> combo){
        String sql = "SELECT "+load_type1+", author_id FROM books WHERE books."+col_type+"=?";
        try(Connection conn = DB_Connection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            System.out.println(combo.getValue());
            ps.setString(1, combo.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                load_com1.setValue(rs.getString(load_type1));
                com_author.setValue(DB_Connection.getNameByID("author_name","author","author_id", rs.getString("author_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadMemberData(String load_type, String col_type, JFXComboBox<String> load_combo, JFXComboBox<String> combo, String table){
        String sql = "SELECT "+load_type+" FROM "+table+" WHERE "+col_type+"=?";
        try(Connection conn = DB_Connection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, combo.getValue());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                load_combo.setValue(rs.getString(load_type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
