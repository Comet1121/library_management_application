package library_management_application.data_model;

import library_management_application.database.DB_Connection;
import sun.security.pkcs11.Secmod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private static Object ArrayList;
    private String book_id;
    private String book_title;
    private String author_name;
    private String category_name;
    private int price;
    private int qty;
    private String location;
    private String available;
    private String supplier_name;

    public Book(String book_id, String book_title, String author_name,String supplier_name, String category_name,
                int price, int qty, String location) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.author_name = author_name;
        this.category_name = category_name;
        this.price = price;
        this.qty = qty;
        this.location = location;
        this.supplier_name = supplier_name;
    }

    public Book(String book_id, String book_title, String author_name, String supplier_name, String category_name,
                int price,
                int qty, String location, String available) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.author_name = author_name;
        this.category_name = category_name;
        this.price = price;
        this.qty = qty;
        this.supplier_name = supplier_name;
        this.location = location;
        if (qty > 0){
            this.available = "available";
        }
        else {
            this.available = "not available";
        }
    }
    public static String setAvailable(int qty){
        String available = "";
        if (qty > 0){
            available = "available";
        }
        else {
            available = "not available";
        }
        return available;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public static ArrayList<Book> getAllList() throws SQLException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList();
        String sql = "SELECT book_id, book_title, author_name,supplier_name, category_name, price,"
                + " qty, location FROM books, author, category, supplier WHERE books.category_id = category" +
                ".category_id AND books.author_id = author.author_id AND books.supplier_id = supplier.supplier_id " +
                "ORDER BY book_id;";

        try(Connection conn = DB_Connection.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);) {
            while (rs.next()) {
               Book b = new Book(rs.getString("book_id"),
                                 rs.getString("book_title"),
                                 rs.getString("author_name"),
                                 rs.getString("supplier_name"),
                                 rs.getString("category_name"),
                                 rs.getInt("price"),
                                 rs.getInt("qty"),
                                 rs.getString("location"),
                                 setAvailable(rs.getInt("qty"))
                                );
               books.add(b);
            }
        }
        catch (SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return books;
    }


    public Boolean addBook(){
        boolean b = false;
        String sql = "INSERT INTO books (book_id, book_title, author_id, supplier_id, category_id, price, qty, " +
                "location) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try(Connection conn = DB_Connection.getConnection();
            ){
            String author_id = DB_Connection.getID("author_id", "author_name", author_name, "author");
            String category_id = DB_Connection.getID("category_id", "category_name", category_name, "category");
            String supplier_id = DB_Connection.getID("supplier_id", "supplier_name", supplier_name, "supplier");

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,getBook_id());
            ps.setString(2,getBook_title());
            ps.setString(3,author_id);
            ps.setString(4,supplier_id);
            ps.setString(5,category_id);
            ps.setInt(6,getPrice());
            ps.setInt(7,getQty());
            ps.setString(8,getLocation());
            ps.executeUpdate();

            b = true;

        } catch (SQLException e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }

}
