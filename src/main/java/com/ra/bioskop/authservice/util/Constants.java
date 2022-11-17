package com.ra.bioskop.authservice.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {
    public static final String SUCCESS_MSG = "success";
    public static final String NOT_FOUND_MSG = "not found";
    public static final String INVALID_EMAIL_MSG = "Invalid email";
    public static final String CREATED_MSG = "created";
    public static final String ALREADY_EXIST_MSG = "already exist";
    public static final String AUTH_ENDPOINT = "/api/auth";

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]" +
            "(.[_A-Za-z0-9-]+)@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)" +
            "(.[A-Za-z]{2,})$";

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";

    /*
     * @Param email
     * 
     * @return true
     */
    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

     /*
     * Random Identifier result :
     * [0] -> time_low
     * [1] -> time_mid
     * [2] -> time_hi_and_version
     * [3] -> clock_seq_hi_and_res
     * [4] -> node
     */
    public static String[] randomIdentifier(String s) {
        byte[] b = s.getBytes();
        UUID uuid = UUID.nameUUIDFromBytes(b);
        return uuid.toString().split("-");
    }
}
