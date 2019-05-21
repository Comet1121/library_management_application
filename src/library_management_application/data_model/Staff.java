package library_management_application.data_model;

import library_management_application.Action.Loader;
import library_management_application.database.DB_Connection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Staff {
    private String staff_id;
    private String staff_name;
    private String password;
    private String role_type;
    private String phone;
    private String address;
    private static Connection conn;

    static {
        try {
            conn = DB_Connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Staff(String staff_id, String staff_name, String password, String role_type, String phone, String address) throws SQLException {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.password = password;
        this.role_type = role_type;
        this.phone = phone;
        this.address = address;
    }

    public Staff(String staff_id, String staff_name, String password, String role_type) throws SQLException {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.password = password;
        this.role_type = role_type;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean addStaff(){
        boolean b = false;
        String sql = "INSERT INTO staff (staff_id, staff_name, password, role_id, phone, address) VALUES (?, ?, ?, ?," +
                " ?, ?)";
        String role_id = DB_Connection.getID("role_id","role_type",role_type,"role");

        try(Connection conn = DB_Connection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,getStaff_id());
            ps.setString(2,getStaff_name());
            ps.setString(3,getPassword());
            ps.setString(4, role_id);
            ps.setString(5, getPhone());
            ps.setString(6, getAddress());
            ps.executeUpdate();

            b = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static void validateStaff(String uname, String password) {

        String sql = "SELECT staff_id, staff_name, password, role_id  FROM staff where staff_name=? and password=?;";
        System.out.println("validate Staff!");

        try {
            conn = DB_Connection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,uname);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                switch(rs.getString("role_id")){
                    case "1": Loader.auth = "admin";break;
                    case "2": Loader.auth = "staff";break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + rs.getString("role_id"));
                }
            }
            else{

            }
        } catch (SQLException e) {
            e.getStackTrace();
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
