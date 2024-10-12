package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.model.BeerExpert;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BeerSelect extends HttpServlet {
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        // 用于获取 html中<select>标记中"name"属性的值
        String c = request.getParameter("color");
        // 实例化BeerExpert类，并调用getBrands()
        BeerExpert beerExpert = new BeerExpert();
        List<String> brands = beerExpert.getBrands(c);

//        // 设置响应的类型, 现在由视图 jsp生成输出
//        response.setContentType("text/html");
//        // 写入 html
//        PrintWriter out = response.getWriter();
//        out.println("Beer Selection Advice<br>");
//
//        // 打印建议
//        for (String brand : brands) {
//            out.println("<br>try: " + brand);
//        }
        // request 对象相当与一个js对象, 设置属性
        request.setAttribute("styles", brands);
        // 为jsp实例化一个 请求分派器
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("result.jsp");
        // 使用 请求分派器需求容器准备好 jsp并向 jsp发送 请求和响应
        requestDispatcher.forward(request,response);
    }
}
