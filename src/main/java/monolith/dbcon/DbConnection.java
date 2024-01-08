package monolith.dbcon;

import java.sql.*;

public class DbConnection 
{
	private static Connection instance;
	private static Object connectionLock;
	
	public static Connection instance() {
		return instance;
	}
	
	public static Object createConnection() {
		if (connectionLock != null) return null;
		try {
			instance = DriverManager.getConnection("jdbc:sqlite:mypaperplugin.db");
			connectionLock = new String();
			return connectionLock;
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
	
	public static void disposeConnection(Object lock) {
		if (lock != connectionLock) return;
		try {
			instance.close();
			connectionLock = null;
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return;
		}
	}
}
