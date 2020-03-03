package com.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class ConnectionManager {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	final static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	private static Connection con;
	
	public static Connection createConnection(String dbUrl,String dbusername,String dbPassword) {
		try {
			Class.forName(DB_DRIVER);
			try {
				con = DriverManager.getConnection(dbUrl, dbusername, dbPassword);
				logger.info("Connected");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			logger.debug(e);
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
            	 logger.debug(ex);
                 ex.printStackTrace();
            }
        }

    }

}
