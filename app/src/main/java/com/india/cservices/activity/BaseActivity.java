package com.india.cservices.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.india.cservices.common.ApiConstants;
import com.india.cservices.inerfaces.INetworkResponse;
import com.india.cservices.inerfaces.trackAppListner;

import org.json.JSONObject;

/**
 * Created by shalini on 6/3/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, trackAppListner, INetworkResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(trackEvent());
        trackEvent();
    }

    @Override
    public void onClick(View v) {

    }

    public abstract void trackEvent();


    protected void setFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void trackApp(String d,String dw) {
        Log.e(""+d,">>>>>>");
    }

    @Override
    public void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType) {

    }

    @Override
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {

    }
}
