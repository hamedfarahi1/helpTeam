package com.kharazmi.helpdesk.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator
{
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile(Varibles.EmailRegex, Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile(Varibles.PasswordRegex);

    public static boolean isEmailValid(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static boolean isPhoneNumberValid(String phoneNumber)
    {
        char[] p = phoneNumber.toCharArray();
        return ((p[0] == '0') && (p[1] == '9') && (p.length == 11)) || ((p[0] == '+') && (p[1] == '9') && (p[2] == '8') && (p.length == 13));
    }

    public static boolean isPasswordValid(String password)
    {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }
}
