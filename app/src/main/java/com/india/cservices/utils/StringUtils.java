package com.india.cservices.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lab1 on 04/10/17.
 */

public class StringUtils {


    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * check email address is valid or not
     * @param emailStr
     * @return
     */
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    /**
     * check String is NULL of not if String is null then return false and also
     * its value equal to blankspace then return false other wise return true .
     * @param value
     * @return
     */
    public static boolean stringIsNull(String value)
    {
        return value != null?value.equalsIgnoreCase(""):false ;
    }
}
