package me.chen.floowa.controller;

import me.chen.floowa.dto.RequestItemDto;
import me.chen.floowa.model.Merchandise;
import me.chen.floowa.service.MerchandiseRequestedService;
import me.chen.floowa.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    MerchandiseService merchandiseService;
    @Autowired
    MerchandiseRequestedService merchandiseRequestedService;

    @GetMapping(value = "/admin/shop")
    public String shop(ModelMap modelMap)
    {
        // Get list of items
        List<Merchandise> merchandises = merchandiseService.findAll();
        modelMap.addAttribute("items", merchandises);
        return "shop";
    }

    /**
     * Save request to database.
     * @param requestItemDto
     * @return
     */
    @PostMapping(value = "/date/shop/item/request")
    public String itemRequest(RequestItemDto requestItemDto){
        merchandiseRequestedService.save(requestItemDto);

        return "redirect:/admin/shop";
    }
}
