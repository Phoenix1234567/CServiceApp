package com.india.cservices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.india.cservices.R;
import com.india.cservices.common.AppConstants;
import com.india.cservices.inerfaces.INetworkResponse;
import com.india.cservices.net.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shalini on 6/3/2017.
 */

public class LoginActivity extends BaseActivity implements INetworkResponse {

    private EditText emailOrPhone;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViewById(R.id.txt_skip).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        emailOrPhone = (EditText) findViewById(R.id.login_edittext_email);
        password = (EditText) findViewById(R.id.login_edittext_pds);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.btn_login:

                VolleyHelper.jsonNetworkRequest("http://172.18.120.127:8112/user/login", true, createParamsForLogin(), this, AppConstants.networkRequestType.LOGIN);
                break;
            default:

        }


    }

    JSONObject createParamsForLogin() {
        JSONObject obj = new JSONObject();
        try {
            if (emailOrPhone.getText().toString().matches("[0-9]+"))
                obj.put("mobileNo", emailOrPhone.getText().toString());
            else
                obj.put("emailId", emailOrPhone.getText().toString());
            obj.put("password", password.getText().toString());
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void trackEvent() {

        super.trackApp("Login Activity", "");
    }


    @Override
    public void onJsonResponse(JSONObject obj, AppConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                Toast.makeText(this, "Successfull login", Toast.LENGTH_LONG).show();
                Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(loginIntent);
                finish();
                break;
        }

    }

    @Override
    public void onError(String error, AppConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
