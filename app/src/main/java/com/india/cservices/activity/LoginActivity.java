package com.india.cservices.activity;

import android.os.Bundle;
import android.view.View;

import com.india.cservices.R;
import com.india.cservices.fragment.LoginFragment;

/**
 * this is a login activity
 * Created by shalini on 6/3/2017.
 */

public class LoginActivity extends BaseActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setFragment(R.id.container,LoginFragment.getInstance(),"");
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        }

    @Override
    public void trackEvent() {
        super.trackApp("Login Activity", "");
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount()>1)
            super.onBackPressed();
        else
            finish();
    }
}
