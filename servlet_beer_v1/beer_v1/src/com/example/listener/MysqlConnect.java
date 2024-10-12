package com.example.listener;

import jakarta.servlet.ServletContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnect {
    public Connection getMysql(ServletContext servletContext) throws IOException, ClassNotFoundException, SQLException {

        String jdbcFile = servletContext.getInitParameter("mysql");

        //
        Properties properties = new Properties();
        properties.load(new FileInputStream(jdbcFile));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver"); // 获取 驱动名
        String url1 = properties.getProperty("url");

        Class.forName(driver);
        Connection connect = DriverManager.getConnection(url1, user, password); // 获取 连接
        return connect;
    }
}
