package me.chen.floowa.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ContextIoCouponEmailPayload {

    private String account_id;
    private String weebhook_id;
    private long timestamp;
    private String token;
    private String signature;

    @JsonProperty(value = "message_data")
    private CouponEmail messageData;

}
