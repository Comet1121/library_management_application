package library_management_application.data_model;

import library_management_application.database.DB_Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Supplier {
    private String supplier_id;
    private String supplier_name;
    private String phone;
    private String address;

    public Supplier(String supplier_id, String supplier_name, String phone, String address) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.phone = phone;
        this.address = address;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
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

    public boolean addSupplier(){
        boolean b = false;
        String sql = "INSERT INTO supplier (supplier_id, supplier_name, address, phone) VALUES(?, ?, ?, ?);";

        try(Connection conn = DB_Connection.getConnection()){

            String supplier_id = DB_Connection.getID("supplier_id", "supplier_name", supplier_name, "supplier");
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getSupplier_id());
            ps.setString(2, getSupplier_name());
            ps.setString(3, getAddress());
            ps.setString(4, getPhone());
            ps.executeUpdate();

            b = true;

        } catch (SQLException e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }
}
