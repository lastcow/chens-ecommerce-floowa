package me.chen.floowa.controller;


import me.chen.floowa.dto.ItemDto;
import me.chen.floowa.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdministrationController {

    @Autowired
    MerchandiseService merchandiseService;
    /**
     * New item
     * @param itemDto
     * @return
     */
    @PostMapping(value = "/data/system/item/add")
    public String newItem(ItemDto itemDto){

        merchandiseService.save(itemDto);

        return "redirect:/admin/shop";
    }
}
