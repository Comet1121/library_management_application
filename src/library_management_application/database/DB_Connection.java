package library_management_application.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.jfoenix.controls.JFXComboBox;
import library_management_application.data_model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DB_Connection {

    private static final String URL = "jdbc:mysql://localhost/library_management";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("success");
        Connection con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        return con;
    }

    public DB_Connection() {
    }

    public static void main(String[] args) {
        try {
            getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setDb_connection(){

    }

    public static String getID(String id,String colname, String name, String table){// get id from existing id
        String sql = "SELECT "+id+" FROM "+table+" WHERE "+colname+"='"+name+"';";
        String rid = "";
        try(Connection conn = getConnection();){
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rid = rs.getString(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rid;
    }
    public static String getNameByID(String name_type, String table, String id_type, String id_value){
        String sql = "SELECT "+name_type+" FROM "+table+" WHERE "+table+"."+id_type+"='"+id_value+"';";
        String name = "";
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                name = rs.getString(name_type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static String setID(String id_type, String table){//to setID for new record
        String s_id = "";
        String sql = "SELECT "+id_type+" FROM "+table+" ORDER BY "+id_type+";";
        try(Connection conn = DB_Connection.getConnection();
            ){
            String last_id =" ";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                last_id = rs.getString(id_type);
            }
            int num_id = Integer.parseInt(last_id.substring(3));


            if(num_id >= 9999){
                int char_value =  (int)last_id.charAt(1)+1;
                char c = (char) char_value;
                s_id =(""+ last_id.charAt(0))+c+"_0001";
            }
            else if(num_id >= 100){
                s_id = last_id.substring(0,3)+"0"+(num_id+1);
            }
            else if(num_id >= 10){
                s_id = last_id.substring(0,3)+"00"+(num_id+1);
            }
            else{
                s_id = last_id.substring(0,3)+"000"+(num_id+1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s_id;
    }

    public static ArrayList<String> getDataList(String colName, String tableName){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT ? FROM ?;";
        try{
            Connection conn = DB_Connection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, colName);
            ps.setString(2, tableName);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(rs.getString(colName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void loadDataForCombobox(JFXComboBox<String> com_name, String colName, String table){
        String sql = "SELECT "+colName+" FROM "+table+" ORDER BY "+colName+";";
        try{
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                com_name.getItems().add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
