package com.india.cservices.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.india.cservices.R;
import com.india.cservices.activity.LoginActivity;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.inerfaces.OnUpdateTitleLister;
import com.india.cservices.net.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lab1 on 26/09/17.
 *
 */

public class LoginFragment extends BaseFragment {

    private EditText mEmailOrPhone;
    private EditText mPassword;
    private Activity mActivity;

    public LoginFragment() {

    }

    public static LoginFragment getInstance(OnUpdateTitleLister titleLister) {
          titleLister.onUpdateTitle("Login",0);
         return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        view.findViewById(R.id.txt_skip).setOnClickListener(LoginFragment.this);
        view.findViewById(R.id.txvChangePsd).setOnClickListener(LoginFragment.this);
        view.findViewById(R.id.btn_login).setOnClickListener(LoginFragment.this);
        view.findViewById(R.id.txt_sign_up).setOnClickListener(LoginFragment.this);
        mEmailOrPhone = (EditText) view.findViewById(R.id.login_edittext_email);
        mPassword = (EditText) view.findViewById(R.id.login_edittext_pds);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.btn_login:
                VolleyHelper.jsonNetworkRequest(ApiConstants.USER_LOGIN_URL, true, createParamsForLogin(), this, ApiConstants.networkRequestType.LOGIN);
                break;
            case R.id.txt_skip:
                saveFrefrence(null,mActivity);
                break;

            case R.id.txt_sign_up:
                setFragment(R.id.container,SignUpFragment.getInstance((LoginActivity)mActivity),"we");
                break;
            case R.id.txvChangePsd:
                setFragment(R.id.container, ChangePSDFragment.getInstance((LoginActivity)mActivity),"we");
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
        super.trackApp("LoginFragment", "");
    }


    @Override
    public void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType) {
        switch (networkRequestType) {
            case LOGIN:
                saveFrefrence(obj,mActivity);
                break;
        }
    }

    @Override
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {
                super.showNetworkError(error, networkRequestType,mActivity);
    }


}