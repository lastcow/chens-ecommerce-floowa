package me.chen.floowa.controller;

import me.chen.floowa.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    /**
     * Show order page.
     * @param modelMap
     * @return
     */
    @GetMapping(value = "/admin/orders")
    public String showOrders(ModelMap modelMap){

        // Get all orders.

        List<OrderDto> orderDtos = new ArrayList<>();

        modelMap.addAttribute("orders", orderDtos);
        return "orders";
    }
}
