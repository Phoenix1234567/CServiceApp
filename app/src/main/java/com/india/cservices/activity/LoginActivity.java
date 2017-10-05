package com.india.cservices.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.india.cservices.R;
import com.india.cservices.fragment.LoginFragment;
import com.india.cservices.inerfaces.OnUpdateTitleLister;

/**
 * this is a login activity
 * Created by shalini on 6/3/2017.
 *
 */

public class LoginActivity extends BaseActivity implements OnUpdateTitleLister {

    TextView mTitle ;
    Toolbar mToolbar ;
    int fragmentType ;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.txvTitle);
        setSupportActionBar(mToolbar);
        setFragment(R.id.container,LoginFragment.getInstance(this),"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //finish(); // close this activity and return to preview activity (if there is any)
            super.onBackPressed();
            manageToolBar(false,getFragmentType(),"Login");
        }

        return super.onOptionsItemSelected(item);
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

        if (getSupportFragmentManager().getBackStackEntryCount()>1) {
            super.onBackPressed();
            manageToolBar(false,0,"Login");
        }
        else
            finish();
    }

    @Override
    public void onUpdateTitle(String title,int fragmentType) {

        switch (fragmentType)
        {
            case 0 :
                manageToolBar(false,0,title);
                break;
            case 1 :
                manageToolBar(true,1,title);
                break;
            case 2 :
                manageToolBar(true,2,title);
                break;
        }
    }

    private void manageToolBar(boolean flag,int value,String title) {
        mToolbar.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(flag);
        getSupportActionBar().setHomeButtonEnabled(flag);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setFragmentType(value);

    }

    public int getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(int fragmentType) {
        this.fragmentType = fragmentType;
    }
}
