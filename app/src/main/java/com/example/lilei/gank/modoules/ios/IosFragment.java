package com.example.lilei.gank.modoules.ios;

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

public class IosFragment extends BaseFragment implements IIosView, OnMyClickListener {

    private IBaseRecyclerPresenter mIosPresenter; // 容器

    private RecyclerView mIosRecyclerView;
    private TechnologyAdapter mIosAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mIosArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mIosFragment = inflater.inflate(R.layout.fragment_ios, null);
        initView(mIosFragment);
        initRecyclerView(mIosFragment);
        mIosPresenter.initPage();
        return mIosFragment;

    }

    private void initView(View v) {
        mIosPresenter = new IosPresenter();
    }

    private void initRecyclerView(View v) {
        mIosRecyclerView = (RecyclerView) v.findViewById(R.id.android_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mIosRecyclerView.setLayoutManager(mLinearLayoutManager);
    }
    @Override
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mIosArrayList = items;
        mIosAdapter = new TechnologyAdapter(mIosArrayList, getContext());
        mIosAdapter.setOnTechnologyClickListener(this);
        mIosRecyclerView.setAdapter(mIosAdapter);
    }

    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mIosArrayList = items;
        mIosAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isStartedRec() {
        return mIosAdapter == null;
    }

    @Override
    public void OnItemClicked(String aim, String cont) {

    }
}
