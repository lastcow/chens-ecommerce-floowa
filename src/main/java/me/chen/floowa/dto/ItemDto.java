package me.chen.floowa.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String name;
    private String category;
    private float customerPrice;
    private float agentPrice;
    private int stockCount;
    private int leadTime;
    private int moqCustomer;
    private int moqAgent;
    private String imageUrl;
    private String description;
}
