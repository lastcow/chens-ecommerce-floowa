package me.chen.floowa.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Security check for external api call
 */
public class ApiSecurity {

    /**
     * Calculate HASH256_HMAC signature.
     * @param message
     * @param secret
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String hmac_sha256(String message, String secret) throws NoSuchAlgorithmException, InvalidKeyException {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secretKey);

        byte[] hash = sha256_HMAC.doFinal(message.getBytes());

        return Hex.encodeHexString(hash);

    }

    /**
     * Calculate HASH1_HMAC signature for request
     * @param baseString
     * @param secret
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String hmac_sha1(String baseString, String secret) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Mac sha1_HMAC = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA1");
        sha1_HMAC.init(secretKey);

        byte[] hash = sha1_HMAC.doFinal(baseString.getBytes());

        return URLEncoder.encode(new String(Base64.encodeBase64(hash)).trim(), "UTF-8");

    }
}
