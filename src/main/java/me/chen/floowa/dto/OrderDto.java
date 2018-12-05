package me.chen.floowa.dto;

import lombok.Data;

@Data
public class OrderDto {

    private String id;
    private int totalItems;
    private float subtotal;
    private float shipping;

    private String status;
}
