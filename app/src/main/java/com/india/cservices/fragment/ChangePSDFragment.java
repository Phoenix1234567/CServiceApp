package com.india.cservices.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.india.cservices.R;
import com.india.cservices.inerfaces.OnUpdateTitleLister;

/**
 * Created by lab1 on 04/10/17.
 *
 */

public class ChangePSDFragment extends BaseFragment {



    public ChangePSDFragment()
    {

    }

    public static Fragment getInstance(OnUpdateTitleLister titleLister) {
        titleLister.onUpdateTitle("Change Password",2);
        return new ChangePSDFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_change_psd,container,false);
        return view ;
    }

    @Override
    public void trackEvent() {

    }
}
