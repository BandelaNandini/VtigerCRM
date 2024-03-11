package generic_Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains reusable methods to read from and modify database
 * 
 * @author Nandini
 */

public class DataBaseUtility {
	private Connection connection;
	private Statement statement;

	public void databaseInit(String url, String username, String password)

	{
		Driver dbdriver = null;
		try {
			dbdriver = new Driver();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbdriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Object> ReadFromDatabase(String query, String colName) {
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Object> list = new ArrayList<Object>();
		try {
			while (result.next()) {
				list.add(result.getObject(colName));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int modifyDatabase(String query) {
		int result = 0;
		try {
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void closeDatabase() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
