package me.chen.floowa.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {

    private String id;
    private String comments;
    private List<CartItemDto> cartItemDtos;
}
