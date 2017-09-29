package com.india.cservices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.india.cservices.R;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.common.AppConstants;
import com.india.cservices.common.SharedPreference;
import com.india.cservices.net.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * this is a login activity
 * Created by shalini on 6/3/2017.
 */

public class LoginActivity extends BaseActivity  {

    private EditText mEmailOrPhone;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        findViewById(R.id.txt_skip).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);
        mEmailOrPhone = (EditText) findViewById(R.id.login_edittext_email);
        mPassword = (EditText) findViewById(R.id.login_edittext_pds);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.btn_login:

                VolleyHelper.jsonNetworkRequest(ApiConstants.USER_LOGIN_URL, true, createParamsForLogin(), this, ApiConstants.networkRequestType.LOGIN);
                break;
            default:

        }
    }

    JSONObject createParamsForLogin() {
        JSONObject obj = new JSONObject();
        try {
            if (mEmailOrPhone.getText().toString().matches("[0-9]+"))
                obj.put("mobileNo", mEmailOrPhone.getText().toString());
            else
                obj.put("emailId", mEmailOrPhone.getText().toString());
            obj.put("password", mPassword.getText().toString());
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
    public void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:

                try {
                    SharedPreference.getInstance(LoginActivity.this).saveUserName(AppConstants.USER_NAME,!obj.isNull(AppConstants.USER_NAME)?obj.getString(AppConstants.USER_NAME):"Gues User");
                    SharedPreference.getInstance(LoginActivity.this).saveUserID(AppConstants.USER_ID,obj.getString(AppConstants.USER_ID));
                    SharedPreference.getInstance(LoginActivity.this).saveToken(AppConstants.TOKEN,obj.getString(AppConstants.TOKEN));
                    SharedPreference.getInstance(LoginActivity.this).saveUserEmailId(AppConstants.EMAIL_ID,obj.getString(AppConstants.EMAIL_ID));
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(this, "Successfull login", Toast.LENGTH_LONG).show();
                Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(loginIntent);
                finish();
                break;
        }
    }

    @Override
    public void onError(String error, ApiConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                Toast.makeText(this, "Please enter valid username and password", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
