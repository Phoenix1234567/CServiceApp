package com.india.cservices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.india.cservices.R;
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
        emailOrPhone = (EditText)findViewById(R.id.login_edittext_email);
        password = (EditText)findViewById(R.id.login_edittext_pds);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);



        JSONObject obj = new JSONObject();
        try {
            if(emailOrPhone.getText().toString().matches("[0-9]+"))
                obj.put("mobileNo",emailOrPhone.getText().toString());
            else
                obj.put("emailId",emailOrPhone.getText().toString());

            obj.put("password",password.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        VolleyHelper.jsonNetworkRequest("http://172.18.120.127:8112/user/login",true,obj,this);

//        Intent loginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
//        startActivity(loginIntent);
//        finish();
    }

    @Override
    public void trackEvent() {

        super.trackApp("Login Activity","");
    }



    @Override
    public void onJsonResponse(JSONObject obj) {

        Toast.makeText(this,"Successfull login",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onError(String error) {

    }
}
