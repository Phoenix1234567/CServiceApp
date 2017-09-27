package com.india.cservices.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.india.cservices.inerfaces.trackAppListner;

/**
 * Created by shalini on 6/3/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, trackAppListner {

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

    @Override
    public void trackApp(String d,String dw) {
        Log.e(""+d,">>>>>>");
    }
}
