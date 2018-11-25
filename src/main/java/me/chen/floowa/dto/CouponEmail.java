package me.chen.floowa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CouponEmail {

    private String message_id;
    private String email_message_id;
    private String subject;
    private long date_received;

    @JsonProperty(value = "addresses")
    private CouponEmailAddress couponEmailAddress;

    @JsonProperty(value = "bodies")
    List<CouponEmailBody> bodies;

}