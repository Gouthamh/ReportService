package com.tecnotree.automatiom.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;
import com.tecnotree.automatiom.routers.Routers;

import net.sf.json.JSONSerializer;

//import com.bulks.endpoints.Routers;

public class MysqlDB {

//	public static Connection getConnection(String jdbcUrl, String username, String password)
//			throws SQLException, ClassNotFoundException {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		return DriverManager.getConnection(jdbcUrl, username, password);
//
//	}

	public static int getCountFromTable(String jdbcUrl, String username, String password, String tableName)
			throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

		String query = "SELECT COUNT(*) AS recordCount FROM " + tableName;

		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			// System.out.println(resultSet.absolute(1));
			if (resultSet.next()) {
				// System.out.println(resultSet.first());
				return resultSet.getInt("recordCount");

			}
		}

		return 0; // Default value if no records found
	}

	public static String mysqltojsonformate(String jdbcUrl, String username, String password, String tableName,String jsonpath)
			throws SQLException, JSONException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

		String query = "SELECT * FROM " + tableName +" order by notificationId asc;";
		System.out.println(query);

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		JSONArray jsonArray = new JSONArray();

		// Get metadata to dynamically fetch column names

		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (resultSet.next()) {
			JSONObject jsonRow = new JSONObject();

			// Iterate through columns dynamically
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				String columnValue = resultSet.getString(i);
				jsonRow.put(columnName, columnValue);
			}

			jsonArray.put(jsonRow).toString();
		}
		System.out.println("jsonArray------->" + jsonArray);
		// Step 6: Print or use the JSON data

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(tableName, new JSONObject().put("response", jsonArray));

		String jsonValue = jsonObject.toString();

		System.out.println(jsonObject);
		System.out.println("jsonValue------>"+ jsonValue);
		String jsonvalue = jsonObject.toString();
		Object object = JsonPath.read(jsonvalue, jsonpath);
		String StringObject = object.toString();
		System.out.println("StringObject------>" + StringObject);

		resultSet.close();
		connection.close();
		return StringObject;

	}
	
	public static void deleteMySqlRecords(String jdbcUrl,String username,String password,String tableName) throws ClassNotFoundException, SQLException {
		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
			Statement stmt = conn.createStatement(); // Create a Statement
			String query= "delete from "+tableName;
			stmt.executeUpdate(query); // Execute the query


			conn.close(); // Close the connection when you're done

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void main(String args[]) throws ClassNotFoundException, SQLException {

		MysqlDB mysqlDB = new MysqlDB();
		// Connection connectios = mysqlDB.getConnection(Routers.jdbcUrl,
		// Routers.username, Routers.password);
		int dbcount = mysqlDB.getCountFromTable(Routers.jdbcUrl, Routers.username, Routers.password,
				"smdb.Automationforindex");
		System.out.println(dbcount);
		String values = mysqlDB.mysqltojsonformate(Routers.jdbcUrl, Routers.username, Routers.password,
				"smdb.Automationforindex","[\"smdb.Automationforindex\"].response[0].systemId");
		System.out.println("values____" + values);
		// mysqltojsonformate(Routers.jdbcUrl, Routers.username, Routers.password,
		// "smdb.BulkNotificationServices");

		//System.out.println(deleteMySqlRecords(Routers.jdbcUrl,Routers.username,Routers.password,"smdb.Automationforindex"));
	}

}
