package me.chen.floowa.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CouponEmailAddressTo {

    private String email;
}
