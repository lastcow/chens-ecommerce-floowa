package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private float price;
    private int qty;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private MerchandiseOrder merchandiseOrder;
}
