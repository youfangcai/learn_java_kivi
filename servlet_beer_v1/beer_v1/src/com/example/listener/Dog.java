package com.example.listener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

// 实现 HttpSessionBindingListener，
// 即：Dog本身也是一个监听者, 监听dog本身 绑定和 解绑定到会话的通知
public class Dog implements HttpSessionBindingListener {
    private String breed;
    public Dog(){
        this.breed = "";
    }
    public Dog(String breed) {
        this.breed = breed;
    }
    public String getBreed() {
        return breed;
    }

    // 绑定
    public void valueBound(HttpSessionBindingEvent event){

    }
    // 解绑定
    public void valueUnbound(HttpSessionBindingEvent event){

    }
}
