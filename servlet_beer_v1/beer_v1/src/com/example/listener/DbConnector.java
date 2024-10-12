package com.example.listener;

import jakarta.servlet.ServletContext;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

public class DbConnector {
    public CouchDbConnector getDbConnector(ServletContext servletContext) {

        String jsonUrl = servletContext.getInitParameter("jsonUrl");

        // 2. 建立一个数据库的连接
        Properties properties1 = new Properties();
        try {
            properties1.load(new FileInputStream(jsonUrl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String db_name = properties1.getProperty("db_name");
        String user = properties1.getProperty("user");
        String password = properties1.getProperty("password");
        String url = properties1.getProperty("url");
        String docId = properties1.getProperty("docId");

        StdHttpClient httpClient;
        try {
            httpClient = (StdHttpClient) new StdHttpClient.Builder()
                    .url(url)
                    .username(user)
                    .password(password)
                    .build();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        // 获取 连接对象 dbConnector
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector dbConnector = dbInstance.createConnector(db_name, true);
        return dbConnector;
    }
}
