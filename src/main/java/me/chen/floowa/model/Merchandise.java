package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Merchandise {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String description;
    private String picUrl;
    private float priceRetail;
    private float priceCustomer;
    private float priceAgent;
    private int minimalOrderCustomer;
    private int minimalOrderAgent;
    private Date startAvailableDate;
    private Date endAvailableDate;
    private boolean selfWarranty;           // Help replace with distributor

    private boolean active;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private ShopCategory shopCategory;

}
