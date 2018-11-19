package me.chen.floowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
