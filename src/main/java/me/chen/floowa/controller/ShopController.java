package me.chen.floowa.controller;

import me.chen.floowa.dto.CartItemDto;
import me.chen.floowa.dto.RequestItemDto;
import me.chen.floowa.dto.ShoppingCartDto;
import me.chen.floowa.model.Address;
import me.chen.floowa.model.Merchandise;
import me.chen.floowa.model.ShoppingCart;
import me.chen.floowa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

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
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AddressService addressService;

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
    public String shoppingCart(ModelMap modelMap, Principal principal, Authentication authentication){

        // Get my shopping cart
        String username = principal.getName();
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        ShoppingCart shoppingCart = shoppingcartService.findByUserName(username);

        List<Address> addresses = addressService.findAddressesByUsername(username);

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
                Optional<? extends GrantedAuthority> agentRole = grantedAuthorities.stream().filter(g ->
                    ((GrantedAuthority) g).getAuthority().equalsIgnoreCase("ROLE_AGENT")).findAny();
                if(agentRole.isPresent()){
                    cartItemDto.setMoq(cartItem.getMerchandise().getMinimalOrderAgent());
                }else{
                    cartItemDto.setMoq(cartItem.getMerchandise().getMinimalOrderCustomer());
                }
//                cartItemDto.setMoq();

                cartItemDtos.add(cartItemDto);
            });
        }

        shoppingCartDto.setCartItemDtos(cartItemDtos);

        modelMap.addAttribute("cart", shoppingCartDto).addAttribute("addresses", addresses);

        return "shoppingcart";
    }


    /**
     * Update shopping cart ordered item's qty
     * @param shoppingCartDto
     * @return
     */
    @PostMapping(value = "/admin/updateorderqty")
    public String updateOrderQty(@ModelAttribute ShoppingCartDto shoppingCartDto, RedirectAttributes redirectAttributes){

        // Update shopping cart item
        shoppingCartDto.getCartItemDtos().forEach(cartItemDto -> {
            cartItemService.updateQty(cartItemDto.getId(), cartItemDto.getQty());
        });

        redirectAttributes.addAttribute("success", true);

        return "redirect:/admin/shoppingcart";
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
