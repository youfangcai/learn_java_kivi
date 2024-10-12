package com.example.web;

import com.example.model.GetD3Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class SendD3JsonServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    GetD3Json myd3Json = new GetD3Json();
    Map<String, Object> d3Json = myd3Json.getD3Json();
    JSONObject jsonObject = new JSONObject(d3Json);

    resp.setContentType("application/json");
    resp.getWriter().write(jsonObject.toString());
    }

//    public static void main(String[] args) throws IOException {
//        GetD3Json myd3Json = new GetD3Json();
//        Map<String, Object> d3Json = myd3Json.getD3Json();
//        System.out.println(d3Json);
//
//    }
}
