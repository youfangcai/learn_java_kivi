package com.example.model;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class GetD3Json {
    public Map<String, Object> getD3Json() throws IOException {
        Properties properties1 = new Properties();
        String properties_File = "D:\\java_apache-tomcat-10.1.28\\apache-tomcat-10.1.28\\webapps\\Beer-v1\\WEB-INF\\couchdb.properties"; // 配置文件名
        properties1.load(new FileInputStream(properties_File));
        // 1. 通过 properties1 获取相关的值
        String db_name = properties1.getProperty("db-name");
        String user = properties1.getProperty("user");
        String password = properties1.getProperty("password");
        // 2. 创建 HttpClient 实例
        StdHttpClient httpClient = (StdHttpClient) new StdHttpClient.Builder()
                .url("http://localhost:5984")
                .username(user)
                .password(password)
                .build();

        // 3. 创建 CouchDbConnector连接数据库
        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector dbConnector = dbInstance.createConnector(db_name, true);

        // 4. 读取文档
        String docId = "95ed1c8088d60c38bf695351bf000071";
//        Map<String, Object> doc = (Map<String, Object>)dbConnector.get(Map.class, docId);
//        Map<String, Object> mapjson = null;
//        // 5. 处理数据
//        if (doc != null) {
//            Map<String, Object> content = (LinkedHashMap<String, Object>)doc.get("content");
        @SuppressWarnings("unchecked")
        Map<String, Object> doc = (Map<String, Object>)dbConnector.get(Map.class, docId);
        Map<String, Object> mapjson = new LinkedHashMap<String, Object>();

        // 5. 处理数据
            if (doc != null) {
                // 检查 "content" 是否存在且是 Map 对象
                @SuppressWarnings("unchecked")
                Map<String, Object> content = (LinkedHashMap<String, Object>) doc.get("content");
                mapjson = content;
            } else {
                System.out.println("Document not found");
            }
            // 显示关闭连接
        httpClient.shutdown();
        return mapjson;
    }
}
