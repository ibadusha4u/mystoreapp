package com.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	private static Connection con;
	
	public static Connection createConnection(String dbUrl,String dbusername,String dbPassword) {
		try {
			Class.forName(DB_DRIVER);
			try {
				con = DriverManager.getConnection(dbUrl, dbusername, dbPassword);
				System.out.println("Connected");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static Connection getConnection(){
        return con;
    }
	
	public static void closeConnection(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }

    }

}
