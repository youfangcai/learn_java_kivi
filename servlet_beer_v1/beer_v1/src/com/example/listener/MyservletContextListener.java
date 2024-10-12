package com.example.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyservletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event){
        // 1.由事件得到 ServletContext
        ServletContext servletContext = event.getServletContext();
        // 使用上下文得到初始化参数
        String dogBreed = servletContext.getInitParameter("breed");
        // 创建一个新的dog类
        Dog dog = new Dog(dogBreed);
        // 使用上下文来设置一个属性,用于应用的其他部分使用
        servletContext.setAttribute("dog", dog);

    }
    public void contextDestroyed(ServletContextEvent event){

    }
}