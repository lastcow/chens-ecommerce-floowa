package me.chen.floowa.dto;

import lombok.Data;


@Data
public class AddressDto {

    private String country;
    private String name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String contact;
    private String company;
    private String memo;
}
