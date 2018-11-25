package me.chen.floowa.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CouponEmailBody {

    private String type;
    private String charset;
    private String body_section;
    private String content;

}
