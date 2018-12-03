package me.chen.floowa.controller;

import me.chen.floowa.dto.CartItemDto;
import me.chen.floowa.dto.RequestItemDto;
import me.chen.floowa.dto.ShoppingCartDto;
import me.chen.floowa.model.Merchandise;
import me.chen.floowa.model.ShoppingCart;
import me.chen.floowa.service.MerchandiseRequestedService;
import me.chen.floowa.service.MerchandiseService;
import me.chen.floowa.service.ShoppingcartService;
import me.chen.floowa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ShopController {

    @Autowired
    MerchandiseService merchandiseService;
    @Autowired
    MerchandiseRequestedService merchandiseRequestedService;
    @Autowired
    UserService userService;
    @Autowired
    ShoppingcartService shoppingcartService;

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

    @PostMapping(value = "/data/shop/item/addtocart", produces = "application/json")
    @ResponseBody
    public boolean addItemToShoppingCart(Principal principal,  @RequestBody  Map<String, Object> payload){
        // Get username
        String userName = principal.getName();

        // Calling store function
        return userService.addItemToShoppingCart(userName,
                payload.get("itemId").toString(),
                Integer.parseInt(payload.get("orderQty").toString()));
    }

    /**
     * Shopping cart page
     * @param modelMap
     * @param principal
     * @return
     */
    @GetMapping(value = "/admin/shoppingcart")
    public String shoppingCart(ModelMap modelMap, Principal principal){

        // Get my shopping cart
        String username = principal.getName();
        ShoppingCart shoppingCart = shoppingcartService.findByUserName(username);

        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCart.getId());


        // Convert cart item to cartItemDto
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        if(shoppingCart == null){
            // Do nothing
        }else {
            shoppingCart.getCartItems().forEach(cartItem -> {
                CartItemDto cartItemDto = new CartItemDto();
                cartItemDto.setId(cartItem.getId());
                cartItemDto.setTitle(cartItem.getMerchandise().getName());
                cartItemDto.setImgUrl(cartItem.getMerchandise().getImgUrl());
                cartItemDto.setQty(cartItem.getQty());
                cartItemDto.setUnitPrice(cartItem.getPrice());

                cartItemDtos.add(cartItemDto);
            });
        }

        shoppingCartDto.setCartItemDtos(cartItemDtos);

        modelMap.addAttribute("cart", shoppingCartDto);

        return "shoppingcart";
    }


    /**
     * Convert shopping cart to invoice that visible to all agents.
     * @param payroll
     * @return
     */
    @PostMapping(value = "/admin/convertcarttoinvoice")
    @ResponseBody
    public boolean convertCartToInvoice(@RequestBody Map<String, Object> payroll){
        boolean success = false;

        return success;
    }
}
