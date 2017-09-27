package com.india.cservices.application;

import android.support.multidex.MultiDexApplication;

import com.india.cservices.net.VolleyHelper;

/**
 * Created by lab1 on 22/09/17.
 *
 */

public class CSApplication extends MultiDexApplication {

    CSApplication csInstance ;

    @Override
    public void onCreate() {
        super.onCreate();
        csInstance = this ;
        VolleyHelper.init(this);
    }
}
