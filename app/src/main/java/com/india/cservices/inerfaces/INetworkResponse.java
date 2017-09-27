package com.india.cservices.inerfaces;

import org.json.JSONObject;

/**
 * Created by Sarvaraj.Singh on 27-09-2017.
 */

public interface INetworkResponse {

    void onJsonResponse(JSONObject obj);
    void onError(String error);
  //  void onStringResponse(String response);
}
