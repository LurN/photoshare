package com.photoshare.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtils {

	
	
	private static Connection conn = null;
	
	public static Connection getConnection(){
		if(conn != null)
			return conn;
		else{
			try {
				Properties propertiesFile = new Properties();
				InputStream inputStream = DatabaseUtils.class.getClassLoader().getResourceAsStream("/database.properties");
				propertiesFile.load(inputStream);
				
				// get connection properties from database.properties file
				String driver = propertiesFile.getProperty("driver");
				String url = propertiesFile.getProperty("url");
				String user = propertiesFile.getProperty("user");
				String password = propertiesFile.getProperty("password");
				
				//instantiate the jdbc driver
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url, user, password);
				
				
			}catch(ClassNotFoundException e){
				//in case the driver class is not found
				e.printStackTrace();
				
			}catch(SQLException e){
				//in case the DriverManager fails to get a connection
				e.printStackTrace();
				
			}catch(FileNotFoundException e){
				//in case we can't find the database.properties file
				e.printStackTrace();
				
			}catch(IOException e){
				//in case we fail to load the properties file
				e.printStackTrace();
			}
			
			return conn;
		}
		
	}//end getConnection
	
	
	
	
	
}
