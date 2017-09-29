package com.india.cservices.net;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.inerfaces.INetworkResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class VolleyHelper {
	
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
	
    public static void init(Context context) {
    	initRequestQueue(context);
    	initImageLoader(context);
    }

    private static void initRequestQueue(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }
    
    private static void initImageLoader(Context context){
        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapCache(cacheSize));
    }
       

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }


    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }



    public static void jsonNetworkRequest(String url, boolean isPost, JSONObject jsonObject, final INetworkResponse listener, final ApiConstants.networkRequestType networkRequestType)
    {


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.e("onresponse", " : " + response.toString());
              listener.onSuccess(response,networkRequestType);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", " : "+error.getMessage());
                listener.onError(error.getMessage(),networkRequestType);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(stringRequest);
    }

     public static void stringNetworkRequest(String url, final INetworkResponse listener, final ApiConstants.networkRequestType networkRequestType) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onresponse", " : " + response.toString());
                        JSONObject jsonObject = null;
                        try {
                            listener.onSuccess( new JSONObject(response),networkRequestType);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error", " : "+error.getMessage());
                        listener.onError(error.getMessage(),networkRequestType);
                    }
                }) {
        };

        mRequestQueue.add(stringRequest);
    }

}
