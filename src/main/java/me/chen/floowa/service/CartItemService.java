package me.chen.floowa.service;

import me.chen.floowa.model.CartItem;
import me.chen.floowa.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }
}
