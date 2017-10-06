package com.india.cservices.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.india.cservices.activity.DashboardActivity;
import com.india.cservices.common.ApiConstants;
import com.india.cservices.common.AppConstants;
import com.india.cservices.common.SharedPreference;
import com.india.cservices.inerfaces.INetworkResponse;
import com.india.cservices.inerfaces.trackAppListner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lab1 on 22/09/17.
 *
 */

public abstract class BaseFragment extends Fragment implements INetworkResponse, View.OnClickListener, trackAppListner {


    public  void setFragment()
    {

    }

    public abstract void trackEvent();

    protected void setFragment(int containerId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void trackApp(String d,String dw) {
        Log.e(""+d,">>>>>>");
    }
    @Override
    public void onSuccess(JSONObject obj, ApiConstants.networkRequestType networkRequestType) {

    }

    @Override
    public void onError(JSONObject error, ApiConstants.networkRequestType networkRequestType) {

    }

    /**
     * save user response from server to shared prefrence after login/register.
     * @param obj
     * @param mActivity
     */
    protected void saveFrefrence(JSONObject obj, Activity mActivity) {
        if (obj!=null)
        {
            try {
                SharedPreference.getInstance(mActivity).saveUserName(AppConstants.USER_NAME, !obj.isNull(AppConstants.USER_NAME) ? obj.getString(AppConstants.USER_NAME) : AppConstants.GUEST_USER);
                SharedPreference.getInstance(mActivity).saveUserID(AppConstants.USER_ID, obj.getString(AppConstants.USER_ID));
                SharedPreference.getInstance(mActivity).saveToken(AppConstants.TOKEN, obj.getString(AppConstants.TOKEN));
                SharedPreference.getInstance(mActivity).saveUserEmailId(AppConstants.EMAIL_ID, obj.getString(AppConstants.EMAIL_ID));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        Intent loginIntent = new Intent(mActivity, DashboardActivity.class);
        startActivity(loginIntent);
        mActivity.finish();
    }

    /**
     * show network error
     * @param error
     * @param networkRequestType
     * @param mActivity
     */
    public void showNetworkError(JSONObject error, ApiConstants.networkRequestType networkRequestType,Activity mActivity)
    {
                try {
                    Toast.makeText(mActivity, "" + error.getString(AppConstants.ERROR_MSG), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                        }
    }
}
