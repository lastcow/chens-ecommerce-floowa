package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class ShoppingCart {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String status;      // Pending, Paid, Shipped
    private boolean agentOnly;  // Is this purchase only allow agent response? If false, then only facing supplier.
    private Date updateAt;
    private Date createdAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
