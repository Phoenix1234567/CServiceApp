package com.india.cservices.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;

import com.india.cservices.R;
import com.india.cservices.fragment.LoginFragment;

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

        setFragment(R.id.container,LoginFragment.getInstance(),"");


    }

    private void setFragment(int containerId, Fragment fragment, String tag) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId,fragment,tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        }

    @Override
    public void trackEvent() {

        super.trackApp("Login Activity", "");
    }

//    }

   /* JSONObject createParamsForLogin() {
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
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                //new JSONObject(error);
                try {
                    Toast.makeText(this, ""+error.getString("errorMessage"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }*/
}
