package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/abhi?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Abhishek@123";

	private static final String INSERT_USERS_SQL = "INSERT INTO demovalues (Voltage,Current,Distance,Duration,Power) VALUES  (?, ?, ?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "select Voltage,Current,Distance,Duration,Power from demovalues where id =?";
	private static final String SELECT_ALL_USERS = "select * from demovalues";
	private static final String DELETE_USERS_SQL = "delete from demovalues where id = ?";
	private static final String UPDATE_USERS_SQL = "update demovalues set Voltage = ?,Current=?,Distance=?,Duration=?,Power=? where id = ?";

	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getVoltage());
			preparedStatement.setString(2, user.getCurrent());
			preparedStatement.setString(3, user.getDistance());
			preparedStatement.setString(4, user.getDuration());
			preparedStatement.setString(5, user.getPower());
		
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String Voltage = rs.getString("Voltage");
				String Current = rs.getString("Current");
				String Distance=rs.getString("Distance");
				String Duration = rs.getString("Duration");
				String Power = rs.getString("Power");
			    user = new User(id, Voltage,Current,Distance,Duration,Power);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();		
		try (Connection connection = getConnection();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("id");
				String Voltage = rs.getString("Voltage");
				String Current = rs.getString("Current");
				String Distance=rs.getString("Distance");
				String Duration = rs.getString("Duration");
				String Power = rs.getString("Power");
				
				users.add(new User(id, Voltage,Current,Distance,Duration,Power));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getVoltage());
			statement.setString(2, user.getCurrent());
			statement.setString(3, user.getDistance());
			statement.setString(4, user.getDuration());
			statement.setString(5, user.getPower());

			statement.setInt(6, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
