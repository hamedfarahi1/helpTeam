package com.kharazmi.helpdesk.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Varibles {
    public static String PasswordRegex= "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"; // can be changed (test)
    public static String KaveNegarApiKey = "";
    public static String EmailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    //farmoon started
    public static String JWTkey = "Xps7hvsZZxc4Y9Kb";
    public static long JWTexpire = 1000 * 60 * 60 * 24 * 10; // 10 days
    public static String PhoneNumberRegex = "09[0-9]{9}";
    //farmoon ended

    private Pattern pattern;
    private Matcher matcher;

    public boolean validate(final String passwordRegex){
        pattern = Pattern.compile(PasswordRegex);
        matcher = pattern.matcher(passwordRegex);
        return matcher.matches();

    }
}
