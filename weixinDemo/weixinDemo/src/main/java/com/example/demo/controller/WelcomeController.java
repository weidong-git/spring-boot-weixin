package com.example.demo.controller;

import com.example.demo.component.wechat.Wechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private Wechat wechat;

    @GetMapping("/")
    public String index(){
        return "hello";
    }


    @GetMapping("/getToken")
    public String getToken(){
        this.wechat.getAccessToken();
        return "ok";
    }
}
