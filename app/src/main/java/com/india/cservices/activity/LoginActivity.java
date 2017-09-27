package com.india.cservices.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.india.cservices.R;
import com.india.cservices.inerfaces.INetworkResponse;

import org.json.JSONObject;

/**
 * Created by shalini on 6/3/2017.
 */

public class LoginActivity extends BaseActivity implements INetworkResponse {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.login_activity);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public void onResponse(JSONObject obj) {

    }

    @Override
    public void onError(String error) {

    }
}
