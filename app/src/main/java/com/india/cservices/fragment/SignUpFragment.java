package com.india.cservices.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.india.cservices.R;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.net.VolleyHelper;
import com.india.cservices.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * this framgent is use for signup in this application.
 * Created by lab1 on 26/09/17.
 * */

public class SignUpFragment extends CSBaseFragment {


    EditText mFirstName;
    EditText mLastName;
    EditText mEmailId;
    EditText mMobileNo;
    EditText mPassword;
    EditText mRefrralCode;



    public SignUpFragment()
    {

    }

    public static Fragment getInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up,container,false);
        initView(view);
        return view ;
    }

    private void initView(View view) {
        mFirstName = (EditText) view.findViewById(R.id.editFirstName);
        mLastName = (EditText) view.findViewById(R.id.editLastName);
        mEmailId = (EditText) view.findViewById(R.id.editEmailId);
        mMobileNo = (EditText) view.findViewById(R.id.editMobileNo);
        mPassword = (EditText) view.findViewById(R.id.editPassword);
        mRefrralCode = (EditText) view.findViewById(R.id.editRefrralCode);
        view.findViewById(R.id.btnSignUp).setOnClickListener(this);

    }

    @Override
    public void trackEvent() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        VolleyHelper.jsonNetworkRequest(ApiConstants.USER_SIGNUP_URL, true, createParamsForSignUp(), this, ApiConstants.networkRequestType.SIGNUP);
    }

    JSONObject createParamsForSignUp() {
        JSONObject obj = new JSONObject();
        try {
            if (!StringUtils.stringIsNull(mFirstName.getText().toString()))
                obj.put("firstName", mFirstName.getText().toString());
            else {
                return null ;
            }
            if (!StringUtils.stringIsNull(mLastName.getText().toString()))
                obj.put("lastName", mLastName.getText().toString());
            else {
                return null ;
            }

            if (!StringUtils.stringIsNull(mEmailId.getText().toString()) && StringUtils.validateEmail(mEmailId.getText().toString()))
                obj.put("emailId", mEmailId.getText().toString());
            else {
                return null ;
            }
            if (!StringUtils.stringIsNull(mMobileNo.getText().toString()) && !mEmailId.getText().toString().equalsIgnoreCase(""))
                obj.put("mobileNo", mMobileNo.getText().toString());
            else {
                return null ;
            }
            if (!StringUtils.stringIsNull(mPassword.getText().toString()) && !mPassword.getText().toString().equalsIgnoreCase(""))
                obj.put("password", mPassword.getText().toString());
            else {
                return null ;
            }

            obj.put("referredCode", mRefrralCode.getText().toString());
            obj.put("userType","Customer");
            obj.put("active",true);
            Log.e("jsonResponse", obj.toString());
            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType) {
        super.onSuccess(obj, networkRequestType);
    }

    @Override
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {
        super.onError(error, networkRequestType);
    }
}
