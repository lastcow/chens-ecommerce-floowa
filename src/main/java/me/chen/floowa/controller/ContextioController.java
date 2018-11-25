package me.chen.floowa.controller;

import me.chen.floowa.dto.ContextIoCouponEmailPayload;
import me.chen.floowa.dto.CouponEmailBody;
import me.chen.floowa.service.NvidiaCouponService;
import me.chen.floowa.utils.ApiSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/contextio")
public class ContextioController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Value("${contextio.key}")
    String key;
    @Value("${contextio.client-secret}")
    String clientSecret;

    @Autowired
    NvidiaCouponService nvidiaCouponService;

    /**
     * Handle coupon email, extract the code to database.
     * @param contextIoCouponEmailPayload
     */
    @PostMapping(value = "/webhooks/couponemail")
    public void handleCouponEmail(@RequestBody ContextIoCouponEmailPayload contextIoCouponEmailPayload){

        // Verify
        boolean authorized = false;
        // Identifying
        try {
            authorized = isAuthorizedRequest(contextIoCouponEmailPayload.getTimestamp()+contextIoCouponEmailPayload.getToken(),
                    clientSecret,
                    contextIoCouponEmailPayload.getSignature());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        // Do nothing if not authorized.
        if(!authorized){
            return;
        }

        // Get account id
        String accountId = contextIoCouponEmailPayload.getAccount_id();
        // Get recipient, coupon email only have one recipient, so we always get first one.
        String recipient = contextIoCouponEmailPayload.getMessageData().getCouponEmailAddress().getCouponEmailAddressTos().get(0).getEmail();
        // Get message id
        String messageId = contextIoCouponEmailPayload.getMessageData().getMessage_id();

        LOGGER.info("Checking coupon email");
        // Get email bodies list
        List<CouponEmailBody> emailBodies = contextIoCouponEmailPayload.getMessageData().getBodies();
        // Only get html message body
        Optional<CouponEmailBody> emailBody = emailBodies.stream().filter(couponEmailBody -> couponEmailBody.getType().equalsIgnoreCase("text/html")).findAny();
        // if present
        String code = null;
        if(emailBody.isPresent()){
            // Find code, only work with official nvidia jtx2 official email
            Pattern nvidiaJtx2CouponCodePattern = Pattern.compile("Enter promo code <strong>\\w+</strong> at checkout");
            Matcher codeMatcher = nvidiaJtx2CouponCodePattern.matcher(emailBody.get().getContent());
            while(codeMatcher.find()){
                String couponStr = codeMatcher.group(0);
                String[] strArray = couponStr.split(" ");

//                code = strArray[3].replaceAll("\\*", "").trim();
                code = strArray[3].replaceAll("<strong>", "").replaceAll("</strong>", "").trim();

            }

        }else{
            LOGGER.error("No html message body found for this email message");
        }


        /**
         * If no code found
         */
        if(code == null){
            // TODO notify admin for no code found for email that matches subject.
            return;
        }

        // Save to database
        nvidiaCouponService.create(code);

    }


    /**
     * Validate API request
     * @param message
     * @param secret
     * @param signature
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    private boolean isAuthorizedRequest(String message, String secret, String signature) throws InvalidKeyException, NoSuchAlgorithmException {

        return ApiSecurity.hmac_sha256(message, secret).equals(signature);
    }
}
