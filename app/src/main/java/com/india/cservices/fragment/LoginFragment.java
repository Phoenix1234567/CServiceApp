package com.india.cservices.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.india.cservices.R;
import com.india.cservices.activity.DashboardActivity;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.common.AppConstants;
import com.india.cservices.common.SharedPreference;
import com.india.cservices.net.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lab1 on 26/09/17.
 *
 */

public class LoginFragment extends CSBaseFragment {

    private EditText mEmailOrPhone;
    private EditText mPassword;
    private Activity mActivity;

    public LoginFragment() {

    }

    public static LoginFragment getInstance() {

        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        view.findViewById(R.id.txt_skip).setOnClickListener(LoginFragment.this);
        view.findViewById(R.id.btn_login).setOnClickListener(LoginFragment.this);
        mEmailOrPhone = (EditText) view.findViewById(R.id.login_edittext_email);
        mPassword = (EditText) view.findViewById(R.id.login_edittext_pds);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mActivity = getActivity();
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
                    SharedPreference.getInstance(mActivity).saveUserName(AppConstants.USER_NAME, !obj.isNull(AppConstants.USER_NAME) ? obj.getString(AppConstants.USER_NAME) : "Gues User");
                    SharedPreference.getInstance(mActivity).saveUserID(AppConstants.USER_ID, obj.getString(AppConstants.USER_ID));
                    SharedPreference.getInstance(mActivity).saveToken(AppConstants.TOKEN, obj.getString(AppConstants.TOKEN));
                    SharedPreference.getInstance(mActivity).saveUserEmailId(AppConstants.EMAIL_ID, obj.getString(AppConstants.EMAIL_ID));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(mActivity, "Successfull login", Toast.LENGTH_LONG).show();
                Intent loginIntent = new Intent(mActivity, DashboardActivity.class);
                startActivity(loginIntent);
                mActivity.finish();
                break;
        }
    }

    @Override
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                try {
                    Toast.makeText(mActivity, "" + error.getString("errorMessage"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}