package com.india.cservices.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.india.cservices.R;
import com.india.cservices.common.AppConstants;
import com.india.cservices.common.SharedPreference;

/**
 * Created by shalini on 6/3/2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splas_activity);
        loadSplash();
    }

    private void loadSplash() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!SharedPreference.getInstance(SplashActivity.this).getUserID(AppConstants.USER_ID).equalsIgnoreCase(""))
                                        laodDashboardScreen();
                                    else
                                        loginFailed();
                                }
                            }

                , AppConstants.SPLASH_LOADING_TIME);
    }

    private void laodDashboardScreen() {
        Intent dashboardIntent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(dashboardIntent);
        finish();
    }
    void loginFailed(){
        Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void trackEvent() {

        super.trackApp("SplaashActivity","");
    }
}
