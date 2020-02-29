package Common;

import java.sql.Statement;
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JdbcTemplate {
	public static Connection getConnection() {
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?serverTimezone=UTC&useSSL=false", "root","1234");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
	public static boolean isConnection(Connection conn) {
		boolean valid=true;
		try {
			if(conn==null || conn.isClosed()) {
				valid=false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return valid;
	}
	
	public static void commit(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void rollback(Connection conn) {
		if(isConnection(conn)) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//Connection ��ü �ݱ�
	public static void close(Connection conn) {
			try {
				if(isConnection(conn)) {
				conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	//Statement or PreparedStatement ��ü �ݱ�
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
			stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void close(ResultSet rs) {
		try {
			if(rs != null) {
			rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
