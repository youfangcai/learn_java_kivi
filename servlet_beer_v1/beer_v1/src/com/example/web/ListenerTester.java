package com.example.web;

import com.example.listener.Dog;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ListenerTester extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("test context attributes set by listener");
        out.println("<br>");
        // 从ServletContext得到dog公共对象
        Dog dog = (Dog)getServletContext().getAttribute("dog");

        out.println("Dog's breed is: " + dog.getBreed());
    }

}
