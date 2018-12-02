package me.chen.floowa.repository;

import me.chen.floowa.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findShoppingCartByUserUsername(String username);

}
