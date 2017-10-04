package com.india.cservices.common;

/**
 * Created by sarvaraj.singh on 28-09-2017.
 */

public class ApiConstants {
    public static enum networkRequestType {
        LOGIN, SIGNUP
    }

    // url for cService app
    public static String BASE_URL = "http://172.18.120.127:8112/" ;
    public static String USER_LOGIN_URL = BASE_URL+"user/login";
    public static String USER_SIGNUP_URL = BASE_URL+"user/register";

}
