package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MerchandiseOrder {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String comments;
    private String purchaseFrom;
    private String status;

    /**
     * Calculated on order only
     */
    private float shippingCost;

    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address shipToAddress;
    /**
     * OrderId used to display on web and for reference.
     */
    @Column(unique = true)
    private String orderId;

    @OneToMany(mappedBy = "merchandiseOrder", fetch = FetchType.EAGER)
    private List<CartItem> orderItems;

    // Who will handle your order
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;


}
