package me.chen.floowa.controller;

import me.chen.floowa.model.Merchandise;
import me.chen.floowa.model.ShoppingCart;
import me.chen.floowa.service.MerchandiseService;
import me.chen.floowa.service.NvidiaCouponService;
import me.chen.floowa.service.ShoppingcartService;
import me.chen.floowa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/data/statistics")
public class StatisticsController {

    @Autowired
    NvidiaCouponService nvidiaCouponService;
    @Autowired
    MerchandiseService merchandiseService;
    @Autowired
    UserService userService;
    @Autowired
    ShoppingcartService shoppingcartService;

    @PostMapping(value = "/nvidiacouponcount")
    public int getTotalUsableNvidiaCoupons(){

        return nvidiaCouponService.getTotalNvidiaCouponsByUsed(false);
    }

    /**
     * Get actived merchandise count.
     * @return
     */
    @PostMapping(value = "/itemscount")
    public int getTotalItemsCount(){

        List<Merchandise> merchandises = merchandiseService.findMerchandiseByActive(true);
        return merchandises == null ? 0 : merchandises.size();
    }

    /**
     * Get Shopping cart items total
     * @param principal
     * @return
     */
    @PostMapping(value = "/totalinshoppingcart")
    public int getTotalInShoppingCart(Principal principal){

        // Get username
        String userName = principal.getName();
        // Get shopping cart
        ShoppingCart shoppingCart = shoppingcartService.findByUserName(userName);

        if(shoppingCart != null){
            return shoppingCart.getCartItems().stream().mapToInt(o -> o.getQty()).sum();
        }

        return 0;
    }
}
