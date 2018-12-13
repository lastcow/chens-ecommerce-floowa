package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String wechat;
    private String qq;
    private String country;

    @Column(unique = true)
    private String email;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "agent")
    private List<MerchandiseOrder> agentOrders;

    @OneToMany(mappedBy = "customer")
    private List<MerchandiseOrder> merchandiseOrders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;
}
