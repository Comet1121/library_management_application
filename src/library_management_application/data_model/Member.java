package library_management_application.data_model;

import library_management_application.database.DB_Connection;

import java.sql.*;

public class Member {
    private String member_id;
    private String member_name;
    private String NRC;
    private String address;
    private String phone;
    private Date member_start_date;
    private Date member_expire_date;

    public Member(String member_id, String member_name, String NRC, String address, String phone,
                  Date member_start_date,
                  Date member_expire_date) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.NRC = NRC;
        this.address = address;
        this.phone = phone;
        this.member_start_date = member_start_date;
        this.member_expire_date = member_expire_date;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getMember_start_date() {
        return member_start_date;
    }

    public void setMember_start_date(Date member_start_date) {
        this.member_start_date = member_start_date;
    }

    public Date getMember_expire_date() {
        return member_expire_date;
    }

    public void setMember_expire_date(Date member_expire_date) {
        this.member_expire_date = member_expire_date;
    }

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public boolean addMember(){
        boolean b = false;
        String sql = "INSERT INTO member (member_id, member_name, NRC,  address, phone, member_start_date, " +
                "member_expire_date) VALUES(?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = DB_Connection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, getMember_id());
            ps.setString(2, getMember_name());
            ps.setString(3, getNRC());
            ps.setString(4, getAddress());
            ps.setString(5, getPhone());
            ps.setDate(6, getMember_start_date());
            ps.setDate(7, getMember_expire_date());
            ps.executeUpdate();
            b = true;

        } catch (SQLException e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

    public void MemberList(){
        String sql = "SELECT * FROM member";

        try(Connection conn = DB_Connection.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Member m = new Member(
                        rs.getString("member_id"),
                        rs.getString("member_name"),
                        rs.getString("NRC"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getDate("member_start_date"),
                        rs.getDate("member_expire_date")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
