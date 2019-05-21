package library_management_application.Item;

import java.sql.*;

public class DBConnect {
	private static final String DBURL = "jdbc:mysql://localhost/sample_sales_ojt",
			USER_NAME = "root",
			PASS_WORD = "";
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(DBURL, USER_NAME, PASS_WORD);
	}
}
