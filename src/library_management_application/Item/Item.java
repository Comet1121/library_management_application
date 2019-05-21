package library_management_application.Item;

import java.sql.*;
import java.util.ArrayList;

import library_management_application.Item.DBConnect.*;

public class Item {
	private int id;
	private String name;
	private double unitPrice;
	private double quantity;
	public Item(int id, String name, double unitPrice, double quantity) {
		this.id = id;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return String.format("ITEM[%04d %s %.02f$ %.02funits]", 
				getId(), getName(), getUnitPrice(), getQuantity());
	}
	public boolean insert() {
		boolean success = false;
		String sql = "insert into item (name, unitprice, quantity) values (?,?,?);";
		try (Connection con = DBConnect.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){
			psmt.setString(1, getName());
			psmt.setDouble(2, getUnitPrice());
			psmt.setDouble(3, getQuantity());
			success = psmt.executeUpdate() > 0;
			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				setId(rs.getInt(1));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return success;
	}
	public boolean update() {
		boolean success = false;
		String sql = "update item set name=?, unitprice=?, quantity=? where id=?;";
		try (Connection con = DBConnect.getConnection();
				PreparedStatement psmt = con.prepareStatement(sql);){
			psmt.setString(1, getName());
			psmt.setDouble(2, getUnitPrice());
			psmt.setDouble(3, getQuantity());
			psmt.setInt(4, getId());
			success = psmt.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return success;
	}
	public static ArrayList<Item> getAllItem(){
		ArrayList<Item> items = new ArrayList<>();
		String sql = "select * from item";
		try (Connection con = DBConnect.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			while (rs.next()) {
				items.add(new Item(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getDouble(3), 
						rs.getDouble(4)));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return items;
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof Item) {
			return ((Item)o).getId() == getId();
		}
		return false;
	}
	public static ArrayList<Item> getDataByQuery(String sql){
		ArrayList<Item> items = new ArrayList<>();
		try (Connection con = DBConnect.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			while (rs.next()) {
				items.add(new Item(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getDouble(3), 
						rs.getDouble(4)));
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return items;
	}
}
