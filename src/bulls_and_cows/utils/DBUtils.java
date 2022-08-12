package bulls_and_cows.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
//	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private static final String DATABASE_NAME = "bullsandcows";
	private static final String USER = "root";
	private static final String PASSWORD= "1234";
	
	//Java와 MySQL Database 연결 메서드, 둘 사이 Connection(통로)를 만들어줌
	public static Connection getConnection() throws SQLException {
//		Class.forName(DRIVER_NAME); //Driver 로딩, JDBC 4.0이후로 모든 드라이버들은 클래스 패스에서 자동으로 로딩됨
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
		return connection;
	}
	
	//테이블 생성 메서드
	public static void dropAndCreateTable() {
		//final String createDB = "CREATE DATABASE" + DATABASE_NAME;
		
		final String dropUserTableQuery = "DROP TABLE IF EXISTS user";
		final String dropRecordTableQuery = "DROP TABLE IF EXISTS record";
		
		//Statement : Query 전달 객체
		Statement stmt;
		Connection conn;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.execute(dropUserTableQuery); //user table 삭제
			stmt.execute(dropRecordTableQuery); //record table 삭제
			
			final String createUserTableQuery = 
		            "CREATE TABLE user (" +
		            "id INT(50) unsigned NOT NULL AUTO_INCREMENT," +
		            "userid VARCHAR(50)," +
		            "password INT(50)," +
		            "PRIMARY KEY (id))";
			
			final String createRecordTableQuery = 
		            "CREATE TABLE record (" +
		            "id INT(50) unsigned NOT NULL AUTO_INCREMENT," +
		            "userid VARCHAR(50)," +
		            "record INT(50)," +
		            "PRIMARY KEY (id))";
		            
			stmt.execute(createUserTableQuery); //user table 생성
			stmt.execute(createRecordTableQuery); //record table 생성
			System.out.println("Table has created.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
