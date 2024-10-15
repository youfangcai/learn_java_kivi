package com.example.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class TestJspServlet  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> hobby_list = new ArrayList<String>();
        String hobby = request.getParameter("hobby");
        if (hobby.equals("horse skiing")){
            hobby_list.add("Fred");
            hobby_list.add("Pradeep");
            hobby_list.add("Philippe");
        }else if(hobby.equals("extreme knitting")){
            hobby_list.add("extreme knitting...");
        }else if(hobby.equals("alpine scuba")){
            hobby_list.add("alpine scuba...");
        }else if(hobby.equals("speed dating")){
            hobby_list.add("speed dating...");
        }
        request.setAttribute("hobby", hobby_list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("testJsp.jsp");
        requestDispatcher.forward(request,response);
    }

}
