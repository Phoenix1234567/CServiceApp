package com.india.cservices.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by sarvaraj.singh on 28-09-2017.
 */

public class BadRequest extends Request {
    private static final String TAG = BadRequest.class.getSimpleName();
  //  private final Gson gson = new Gson();


    private final Response.Listener listener;

    public BadRequest(String url, Response.Listener responseListener, Response.ErrorListener listener) {
        super(Request.Method.GET, url, listener);
        Log.e(TAG, "Requesting url : " + url);
        this.listener = responseListener;
    }
    public BadRequest(int method, String url, Response.Listener responseListener, Response.ErrorListener listener) {
        super(method, url, listener);
        Log.e(TAG, "Requesting url : " + url);
        this.listener = responseListener;
    }

    @Override
    public Response parseNetworkResponse(NetworkResponse response) {

        try {
            String json = null;
            json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            JSONObject result = new JSONObject(json);

            if (!result.getBoolean("Status"))
                return Response.success(
                        result.get("Data"),
                        HttpHeaderParser.parseCacheHeaders(response));
            else
                return Response.error(new VolleyError(result.getString("Message")));
        } catch (UnsupportedEncodingException e) {

            return Response.error(new ParseError(e));

        }  catch (JSONException e) {
            return Response.error(new ParseError(e));
        }


    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return volleyError;
    }

    @Override
    protected void deliverResponse(Object response) {
        listener.onResponse(response);
    }


}