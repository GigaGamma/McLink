package link.mc.data;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import link.mc.McLink;

public class Database {
	
	private Connection connection;
	
	public Database(File file) {
		this(file.getAbsolutePath());
	}
	
	public Database(String file) {
		Connection c = null;
	      
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + file);
			c.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.connection = c;
		McLink.instance.getLogger().info("Database at " + file + " successfully opened!");
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void commit() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*public void closeConnection() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public Statement createStatement() {
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public ResultSet execQuery(String query) {
			try {
				return createStatement().executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}
	
}
