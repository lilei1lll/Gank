package com.example.lilei.gank.modoules.frontend;


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
import com.example.lilei.gank.modoules.webViewContainer.WebViewContainerActivity;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/4.
 */

public class FrontendFragment extends BaseFragment implements IFrontendView, OnMyClickListener {

    private IBaseRecyclerPresenter mFrontendPresenter; // 容器

    private RecyclerView mFrontendRecyclerView;
    private TechnologyAdapter mFrontendAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mFrontendArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFrontendFragment = inflater.inflate(R.layout.fragment_frontend, null);
        initView(mFrontendFragment);
        initRecyclerView(mFrontendFragment);
        mFrontendPresenter.initPage();
        return mFrontendFragment;
    }

    private void initView(View v) {
        mFrontendPresenter = new FrontendPresenter(this);
    }

    private void initRecyclerView(View v) {
        mFrontendRecyclerView = (RecyclerView) v.findViewById(R.id.frontend_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mFrontendRecyclerView.setLayoutManager(mLinearLayoutManager);
    }


    @Override
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mFrontendArrayList = items;
        mFrontendAdapter = new TechnologyAdapter(mFrontendArrayList, getContext());
        mFrontendAdapter.setOnTechnologyClickListener(this);
        mFrontendRecyclerView.setAdapter(mFrontendAdapter);
    }

    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mFrontendArrayList = items;
        mFrontendAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isStartedRec() {
        return mFrontendAdapter == null;
    }

    @Override
    public void OnItemClicked(String aim, String cont) {
        startIntentActivity(this, new WebViewContainerActivity(), "webViewUrl", aim, "webViewTitle", cont);
    }
}
