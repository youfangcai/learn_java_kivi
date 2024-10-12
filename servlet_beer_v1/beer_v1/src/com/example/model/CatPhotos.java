package com.example.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
public class CatPhotos {
        public  List<String> getCatPhotos() throws IOException, InterruptedException {
            List<String> catPhotos = new ArrayList<String>();
            for (int i = 0; i < 5; i++) {
                // 1. 创建 URL对象以及 url
                String urlStr = "https://api.thecatapi.com/v1/images/search";
                @SuppressWarnings("deprecation")
                URL url = new URL(urlStr);
                // 2. 通过 URL实例对象来创建 HttpURLConnection链接， 即与 api的链接
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 3. 设置 请求的方法和请求头
                connection.setRequestMethod("GET");
                // Accept 表示希望接收的响应类型
                connection.setRequestProperty("Accept", "application/json");

                // 4. 设置 URL连接的输入/输出， 默认为 false
                connection.setDoInput(true);
                // 5. 处理 api 的响应
                // (1) getResponseCode()表示返回的响应的状态， 200 表示成公
                if (connection.getResponseCode() == 200) {
                    // (2) 创建 输入流, 用于传送是数据
                    InputStream inputStream = connection.getInputStream();
                    // (3) 读取 响应流的内容，并将其转换成字符串
                    String catStr = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                    // (4) JSONArray()通过实例化，把 json顶层数组文本 反序列化为对象
                    // 注意： JSONObject(), 用于反序列化 json对象文本
                    JSONArray catJsonArray = new JSONArray(catStr);
                    // (5) 就是访问属性(只不过值为json对象)
                    JSONObject catJsonObject = catJsonArray.getJSONObject(0);
                    // getString(key)用于访问属性的值
                    catPhotos.add(catJsonObject.getString("url"));
                } else {
                    throw new IOException("HTTP错误代码: " + connection.getResponseCode());
                }
                connection.disconnect();
            }
//            for (String catPhoto : catPhotos) {
//                System.out.println(catPhoto);
//            }
            return catPhotos;
        }
}
