package me.chen.floowa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

    @GetMapping(value = "/")
    public String index(){
        return "index";
    }
}
