package me.chen.floowa.service;

import me.chen.floowa.model.ShoppingCart;
import me.chen.floowa.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingcartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart findByUserName(String username){
        return shoppingCartRepository.findShoppingCartByUserUsername(username);
    }

    public ShoppingCart save(ShoppingCart shoppingCart){
        return shoppingCartRepository.save(shoppingCart);
    }
}
