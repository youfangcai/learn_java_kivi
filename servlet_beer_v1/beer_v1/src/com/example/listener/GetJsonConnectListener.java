package com.example.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.ektorp.CouchDbConnector;
import org.ektorp.http.StdHttpClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class GetJsonConnectListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        // 1. 得到上下文初始化参数
        ServletContext servletContext = event.getServletContext();

        // 2. 设置 上下文属性, dbCouch数据库的连接 给应用的其他地方的部分使用
        DbConnector dbConnector = new DbConnector();
        CouchDbConnector dbConnect = dbConnector.getDbConnector(servletContext);
        servletContext.setAttribute("dbConnect", dbConnect);

        // 3. mysql的连接
        MysqlConnect jdbcConnect = new MysqlConnect();
        try {
            Connection mysqlConnect = jdbcConnect.getMysql(servletContext);
            servletContext.setAttribute("mysqlConnect", mysqlConnect);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

        // 1. 关闭 dbCouch数据库的连接
        StdHttpClient dbConnect = (StdHttpClient)servletContext.getAttribute("dbConnect");
        if (dbConnect != null) {
            try{
                dbConnect.shutdown();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        // 2. 关闭 mysql数据库的连接
        Connection mysqlConnect = (Connection)servletContext.getAttribute("mysqlConnect");
        if (mysqlConnect != null) {
            try{
                mysqlConnect.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
