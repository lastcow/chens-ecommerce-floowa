package me.chen.floowa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CouponEmailAddress {

    @JsonProperty(value = "from")
    private CouponEmailAddressFrom couponEmailAddressFrom;

    @JsonProperty(value = "to")
    private List<CouponEmailAddressTo> couponEmailAddressTos;
}