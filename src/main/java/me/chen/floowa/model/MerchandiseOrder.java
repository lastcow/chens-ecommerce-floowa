package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MerchandiseOrder {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @OneToMany(mappedBy = "merchandiseOrder", fetch = FetchType.EAGER)
    private List<CartItem> orderItems;

    // Who will handle your order
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User agent;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    private String status;
}
