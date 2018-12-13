package me.chen.floowa.controller;

import me.chen.floowa.model.Address;
import me.chen.floowa.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping(value = "/admin/address")
    public String showAddresses(ModelMap modelMap, Principal principal){

        String userName = principal.getName();
        List<Address> addresses = addressService.findAddressesByUsername(userName);

        modelMap.addAttribute("addresses", addresses);

        return "address";
    }
}
