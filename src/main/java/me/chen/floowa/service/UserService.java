package me.chen.floowa.service;

import me.chen.floowa.model.CartItem;
import me.chen.floowa.model.Merchandise;
import me.chen.floowa.model.ShoppingCart;
import me.chen.floowa.model.User;
import me.chen.floowa.repository.ShoppingCartRepository;
import me.chen.floowa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingcartService shoppingcartService;
    @Autowired
    MerchandiseService merchandiseService;
    @Autowired
    CartItemService cartItemService;

    /**
     * Create new user
     * @param user
     * @return
     */
    public User create(User user){
        return userRepository.save(user);
    }

    /**
     * Get all users
     * @return
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Get user details by username.
     * @param username
     * @return
     */
    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    /**
     * Add cart item to shopping cart.
     * @param userName
     * @param itemId
     * @param qty
     * @return
     */
    public boolean addItemToShoppingCart(String userName, String itemId, int qty){

        // Get user based on userName
        User user = userRepository.findUserByUsername(userName);
        if(user == null) return false;

        // Get Merchandise
        Optional<Merchandise> merchandise = merchandiseService.findById(itemId);
        if(!merchandise.isPresent()) return false;

        // Get shopping card first
        ShoppingCart shoppingCart = shoppingcartService.findByUserName(userName);
        if(shoppingCart == null){
            // Create one
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCart.setCreatedAt(new Date());
            shoppingCart.setUpdateAt(new Date());
            shoppingcartService.save(shoppingCart);
        }

        // Add item to shopping cart
        Merchandise selectedMerchandise = merchandise.get();
        // Get price
        float price;
        switch(user.getRole().getName()){
            case "ROLE_AGENT":
                price = selectedMerchandise.getPriceAgent();
                break;
            case "ROLE_CUSTOMER":
                price = selectedMerchandise.getPriceCustomer();
                break;
            default:
                price = -1;
        }

        CartItem cartItem = new CartItem();
        cartItem.setMerchandise(merchandise.get());
        cartItem.setPrice(price);
        cartItem.setQty(qty);
        cartItem.setShoppingCart(shoppingCart);
        cartItemService.save(cartItem);

        // Set update date of shopping cart
        shoppingCart.setUpdateAt(new Date());
        shoppingcartService.save(shoppingCart);

        return true;
    }
}
