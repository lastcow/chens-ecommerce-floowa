package me.chen.floowa.controller;

import me.chen.floowa.dto.AddressDto;
import me.chen.floowa.model.Address;
import me.chen.floowa.model.Country;
import me.chen.floowa.model.User;
import me.chen.floowa.service.AddressService;
import me.chen.floowa.service.CountryService;
import me.chen.floowa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    CountryService countryService;
    @Autowired
    UserService userService;

    /**
     * Show page
     * @param modelMap
     * @param principal
     * @return
     */
    @GetMapping(value = "/admin/address")
    public String showAddresses(ModelMap modelMap, Principal principal){

        String userName = principal.getName();
        List<Address> addresses = addressService.findAddressesByUsername(userName);

        List<Country> countries = countryService.getCountries();

        modelMap.addAttribute("addresses", addresses).addAttribute("countries", countries);

        return "address";
    }


    /**
     * Save new address
     * @param addressDto
     * @param principal
     * @return
     */
    @PostMapping(value = "/data/address/add")
    public String newAddress(AddressDto addressDto, Principal principal){
        String username = principal.getName();
        User user = userService.findUserByUsername(username);

        addressService.saveAddress(addressDto, user);

        return "redirect:/admin/address";
    }
}
