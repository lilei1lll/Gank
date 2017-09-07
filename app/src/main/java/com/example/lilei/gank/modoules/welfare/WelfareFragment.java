package com.example.lilei.gank.modoules.welfare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilei.gank.R;
import com.example.lilei.gank.adapter.WelfareAdapter;
import com.example.lilei.gank.base.BaseFragment;
import com.example.lilei.gank.base.IBaseRecyclerPresenter;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.OnMyClickListener;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/4.
 */

public class WelfareFragment extends BaseFragment implements IWelfareView, OnMyClickListener {

    private IBaseRecyclerPresenter mWelfarePresenter;

    private RecyclerView mWelfareRecyclerView;
    private WelfareAdapter mWelfareAdapter = null;
    private ArrayList<FirstLevelInterfaceItem> mWelfareArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View welfareView = inflater.inflate(R.layout.fragment_welfare, null);

        initView(welfareView);
        initRecyclerView(welfareView);
        mWelfarePresenter.initPage();

        return welfareView;
    }

    private void initView(View v){
        mWelfarePresenter = new WelfarePresenter(this);
    }

    private void initRecyclerView(View v){
        mWelfareRecyclerView = (RecyclerView) v.findViewById(R.id.welfare_recyclerView);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mWelfareRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mWelfareArrayList = items;
        mWelfareAdapter = new WelfareAdapter(mWelfareArrayList, getContext());
        mWelfareAdapter.setMyClickListener(this);
        mWelfareRecyclerView.setAdapter(mWelfareAdapter);

        //TODO 下拉加载更多
    }

    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mWelfareArrayList = items;
        mWelfareAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean isStartedRec() {
        return mWelfareAdapter == null;
    }

    @Override
    public void OnItemClicked(String aim, String cont) {
        ToastUtil.show("明确告诉你，点击后的效果还没做！");
    }
}
