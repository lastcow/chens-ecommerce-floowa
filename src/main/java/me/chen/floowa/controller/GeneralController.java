package me.chen.floowa.controller;

import me.chen.floowa.model.CouponNvidia;
import me.chen.floowa.service.NvidiaCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GeneralController {

    @Autowired
    NvidiaCouponService nvidiaCouponService;

    @GetMapping(value = {"/login", "/"} )
    public String login(){
        return "login";
    }

    @GetMapping(value = "/admin/dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping(value = "/admin/shop")
    public String shop(){
        return "shop";
    }

    @GetMapping(value = "/admin/users")
    public String users(){
        return "users";
    }

}
