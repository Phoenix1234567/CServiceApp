package com.india.cservices.inerfaces;

import com.india.cservices.common.ApiConstants;
import com.india.cservices.common.AppConstants;

import org.json.JSONObject;

/**
 * Created by Sarvaraj.Singh on 27-09-2017.
 */

public interface INetworkResponse {

    void onJsonResponse(JSONObject obj, ApiConstants.networkRequestType networkRequestType);
    void onError(String error, ApiConstants.networkRequestType networkRequestType);
  //  void onStringResponse(String response);
}
