package me.chen.floowa.dto;

import lombok.Data;

@Data
public class CartItemDto {

    private String id;
    private String imgUrl;
    private String title;
    private float unitPrice;
    private int qty;

    /**
     * Minimal order qty
     */
    private int moq;

}


