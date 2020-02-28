package com.mystore.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.core.util.ConnectionManager;

public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
    	
        ServletContext context=sce.getServletContext();
        
        String dburl=context.getInitParameter("DB_CONNECTION");
        String dbusername=context.getInitParameter("DB_USER");
        String dbpassword=context.getInitParameter("DB_PASSWORD");

        ConnectionManager.createConnection(dburl, dbusername, dbpassword);       
    }

    public void contextDestroyed(ServletContextEvent sce) {
    	ConnectionManager.closeConnection();
    }

}
