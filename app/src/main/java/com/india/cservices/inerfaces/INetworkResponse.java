package com.india.cservices.inerfaces;

import com.india.cservices.common.ApiConstants;

import org.json.JSONObject;

/**
 * Created by Sarvaraj.Singh on 27-09-2017.
 *
 */

public interface INetworkResponse {

    void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType);
    void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType);
  //  void onStringResponse(String response);
}
