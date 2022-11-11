package com.ra.bioskop.authservice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Constants {
    public static final String NOT_FOUND_MSG = "not found";
    public static final String ALREADY_EXIST_MSG = "already exist";
    public static final String AUTH_ENDPOINT = "/api/auth";
    public static final String NOTIFICATION_ENDPOINT = "/api/notification";

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
}
