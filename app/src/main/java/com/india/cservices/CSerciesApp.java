package com.india.cservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.india.cservices.net.VolleyHelper;

public class CSerciesApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VolleyHelper.init(this);
    }
}
