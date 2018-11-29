package me.chen.floowa.dto;

import lombok.Data;

@Data
public class RequestItemDto {

    private String name;
    private String itemUrl;
    private float targetPrice;
    private int targetQty;
    private String description;
}
