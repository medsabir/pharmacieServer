package crawler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
	 private static Connection connection;
	 static{
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacies","root","");
		//System.out.println("création d'une connection");
		 } catch (Exception e) {
			e.printStackTrace();
		}
	 } 
	 
	public static Connection getConnection() {
		return connection;
	}
	 
	 

}
