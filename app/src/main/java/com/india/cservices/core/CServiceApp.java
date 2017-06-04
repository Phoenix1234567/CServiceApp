package com.india.cservices.core;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.india.cservices.net.VolleyHelper;

/**
 * Created by shalini on 6/4/2017.
 */

public class CServiceApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.init(this);
    }
}


