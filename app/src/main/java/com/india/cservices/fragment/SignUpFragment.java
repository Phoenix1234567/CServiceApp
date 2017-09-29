package com.india.cservices.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.india.cservices.R;

/**
 * this framgent is use for signup in this application.
 * Created by lab1 on 26/09/17.
 * */

public class SignUpFragment extends CSBaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up,container,false);
        return view ;
    }

    @Override
    public void trackEvent() {

    }
}
