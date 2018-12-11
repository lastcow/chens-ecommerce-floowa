package me.chen.floowa.service;

import me.chen.floowa.model.CartItem;
import me.chen.floowa.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    /**
     * Update item qty.
     * @param id
     * @param qty
     * @return
     */
    public CartItem updateQty(String id, int qty){
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if(cartItemOptional.isPresent()){
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQty(qty);

            return this.save(cartItem);
        }

        return null;
    }
}
