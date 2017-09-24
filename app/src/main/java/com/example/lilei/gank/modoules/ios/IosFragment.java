package com.example.lilei.gank.modoules.ios;

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

public class IosFragment extends BaseFragment implements IIosView, OnStartWebViewListener {

    private IBaseRecyclerPresenter mIosPresenter; // 容器

    private RecyclerView mIosRecyclerView;
    private TechnologyAdapter mIosAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mIosArrayList;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mIosFragment = inflater.inflate(R.layout.fragment_ios, null);
        initView(mIosFragment);
        initRecyclerView(mIosFragment);
        mIosPresenter.initPage(page);
        return mIosFragment;

    }

    private void initView(View v) {
        mIosPresenter = new IosPresenter(this);
    }

    private void initRecyclerView(View v) {
        mIosRecyclerView = (RecyclerView) v.findViewById(R.id.ios_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mIosRecyclerView.setLayoutManager(mLinearLayoutManager);
        mIosRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem = -1;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (page <= C.MAX_PAGE){
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mIosAdapter.getItemCount()){
                        page = page + 1;
                        mIosAdapter.changeMoreStatus(TechnologyAdapter.LOADING_MORE);
                        mIosPresenter.loadDataFromWeb(page);
                        mIosAdapter.changeMoreStatus(TechnologyAdapter.PULLUP_LOAD_MORE);
                    }
                }else {
                    mIosAdapter.changeMoreStatus(TechnologyAdapter.NO_DATA_MORE);
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
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mIosArrayList = items;
        mIosAdapter = new TechnologyAdapter(mIosArrayList, getContext());
        mIosAdapter.setOnTechnologyClickListener(this);
        mIosRecyclerView.setAdapter(mIosAdapter);
    }

    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mIosAdapter.addList(items);
    }

    @Override
    public boolean isStartedRec() {
        return mIosAdapter == null;
    }

    @Override
    public void OnItemClicked(FirstLevelInterfaceItem item) {
        startIntentActivity(this, new TechnologyWebViewContainerActivity(), "clickedBean", item);
    }

}
