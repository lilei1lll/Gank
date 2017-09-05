package com.example.lilei.gank.modoules.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilei.gank.R;
import com.example.lilei.gank.adapter.TechnologyAdapter;
import com.example.lilei.gank.base.BaseFragment;
import com.example.lilei.gank.base.IBaseRecyclerPresenter;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.OnMyClickListener;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/4.
 */

public class AndroidFragment extends BaseFragment implements IAndroidView, OnMyClickListener {

    private IBaseRecyclerPresenter mAndroidPresenter; // 容器

    private RecyclerView mAndroidRecyclerView;
    private TechnologyAdapter mAndroidAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mAndroidArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mAndroidFragment = inflater.inflate(R.layout.fragment_android, null);

        initView(mAndroidFragment);
        return mAndroidFragment;
    }


    private void initView(View v){
        mAndroidPresenter = new AndroidPresenter(this);  // 注入执行类

        mAndroidRecyclerView = (RecyclerView) v.findViewById(R.id.android_recyclerView);
    }

    @Override
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAndroidRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAndroidAdapter = new TechnologyAdapter(mAndroidArrayList, getContext());
        mAndroidAdapter.setOnTechnologyClickListener(this);
        mAndroidRecyclerView.setAdapter(mAndroidAdapter);
        // TODO 下拉刷新
        mAndroidRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }

    @Override
    public void addDataToRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {

    }

    @Override
    public void replaceDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {

    }


    @Override
    public void OnItemClicked(String aim) {

    }
}
