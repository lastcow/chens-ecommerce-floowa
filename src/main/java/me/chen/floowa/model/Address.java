package me.chen.floowa.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;
    private String country;
    private String state;
    private String city;
    private String address1;
    private String address2;
    private String company;
    private String postcode;
    private String contact;
    private String memo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
