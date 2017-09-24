package com.example.lilei.gank.modoules.frontend;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.adapter.TechnologyAdapter;
import com.example.lilei.gank.base.BaseFragment;
import com.example.lilei.gank.base.IBaseRecyclerPresenter;
import com.example.lilei.gank.component.OnStartWebViewListener;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.webViewContainer.TechnologyWebViewContainerActivity;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/4.
 */

public class FrontendFragment extends BaseFragment implements IFrontendView, OnStartWebViewListener {

    private IBaseRecyclerPresenter mFrontendPresenter; // 容器

    private RecyclerView mFrontendRecyclerView;
    private TechnologyAdapter mFrontendAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mFrontendArrayList;
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mFrontendFragment = inflater.inflate(R.layout.fragment_frontend, null);
        initView(mFrontendFragment);
        initRecyclerView(mFrontendFragment);
        mFrontendPresenter.initPage(page);
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
        mFrontendRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem = -1;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (page <= C.MAX_PAGE){
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mFrontendAdapter.getItemCount()){
                        page = page + 1;
                        mFrontendAdapter.changeMoreStatus(TechnologyAdapter.LOADING_MORE);
                        mFrontendPresenter.loadDataFromWeb(page);
                        mFrontendAdapter.changeMoreStatus(TechnologyAdapter.PULLUP_LOAD_MORE);
                    }
                }else {
                    mFrontendAdapter.changeMoreStatus(TechnologyAdapter.NO_DATA_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
//        mFrontendArrayList.addAll(items);
//        mFrontendAdapter.notifyDataSetChanged();
        mFrontendAdapter.addList(items);
    }

    @Override
    public boolean isStartedRec() {
        return mFrontendAdapter == null;
    }

    @Override
    public void OnItemClicked(FirstLevelInterfaceItem item) {
        startIntentActivity(this, new TechnologyWebViewContainerActivity(), "clickedBean", item);
    }
}
