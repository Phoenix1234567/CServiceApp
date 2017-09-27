package com.india.cservices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.india.cservices.R;
import com.india.cservices.inerfaces.INetworkResponse;

import org.json.JSONObject;

/**
 * Created by shalini on 6/3/2017.
 */

public class LoginActivity extends BaseActivity implements INetworkResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViewById(R.id.txt_skip).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void trackEvent() {

        super.trackApp("Login Activity","");
    }



    @Override
    public void onJsonResponse(JSONObject obj) {

    }

    @Override
    public void onError(String error) {

    }
}
