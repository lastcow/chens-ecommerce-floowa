package me.chen.floowa.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {

    private String id;
    private String comments;
    private String purchasefrom;
    private String shipto;
    private List<CartItemDto> cartItemDtos;
}
