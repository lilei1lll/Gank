package com.example.lilei.gank.modoules.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseFragment;

/**
 * Created by lilei on 2017/9/4.
 */

public class AndroidFragment extends BaseFragment{

    private View mAndroidFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAndroidFragment = inflater.inflate(R.layout.fragment_android, null);
        return mAndroidFragment;
    }
}
