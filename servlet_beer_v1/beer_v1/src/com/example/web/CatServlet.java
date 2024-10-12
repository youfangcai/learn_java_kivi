package com.example.web;

import com.example.model.CatPhotos;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // 实例化BeerExpert类，并调用getBrands()
        CatPhotos catPhotos = new CatPhotos();
        List<String> photos = new ArrayList<String>();
        try {
            photos = catPhotos.getCatPhotos();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("styles", photos);
        // 为jsp实例化一个 请求分派器
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("result1.jsp");
        // 使用 请求分派器需求容器准备好 jsp并向 jsp发送 请求和响应
        requestDispatcher.forward(req,res);

    }
}
