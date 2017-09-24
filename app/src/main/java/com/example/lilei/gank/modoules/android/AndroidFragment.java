package com.example.lilei.gank.modoules.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class AndroidFragment extends BaseFragment implements IAndroidView, OnStartWebViewListener {

    private IBaseRecyclerPresenter mAndroidPresenter; // 容器

    private RecyclerView mAndroidRecyclerView;
    private TechnologyAdapter mAndroidAdapter = null;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<FirstLevelInterfaceItem> mAndroidArrayList;

    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mAndroidFragment = inflater.inflate(R.layout.fragment_android, null);
        initView(mAndroidFragment);
        initRecyclerView(mAndroidFragment);
        mAndroidPresenter.initPage(page);
        return mAndroidFragment;
    }


    private void initView(View v){
        mAndroidPresenter = new AndroidPresenter(this);  // 注入执行类
    }

    private void initRecyclerView(View v){
        mAndroidRecyclerView = (RecyclerView) v.findViewById(R.id.android_recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mAndroidRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        Log.d("TAG", "startRecyclerAdapter: "+items+"\n"+items.get(0).get_id());
        mAndroidArrayList = items;
        mAndroidAdapter = new TechnologyAdapter(mAndroidArrayList, getContext());
        mAndroidAdapter.setOnTechnologyClickListener(this);
        mAndroidRecyclerView.setAdapter(mAndroidAdapter);
        // 下拉刷新
        mAndroidRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem = -1;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (page <= C.MAX_PAGE){
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAndroidAdapter.getItemCount()){
                        page = page + 1;
                        mAndroidAdapter.changeMoreStatus(TechnologyAdapter.LOADING_MORE);
                        mAndroidPresenter.loadDataFromWeb(page);
                        mAndroidAdapter.changeMoreStatus(TechnologyAdapter.PULLUP_LOAD_MORE);
                    }
                }else {
                    mAndroidAdapter.changeMoreStatus(TechnologyAdapter.NO_DATA_MORE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    /**
     * 由java分配内存机制可知 mAndroidArrayList 和 mAndroidAdapter中的ArrayList<FirstLevelInterfaceItem>是一致的
     * @param items ArrayList<FirstLevelInterfaceItem>
     */
    @Override
    public void changeDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items) {
        mAndroidArrayList.addAll(items);
        mAndroidAdapter.notifyDataSetChanged();
//        mAndroidAdapter.addList(items);
    }

    /**
     * mAndroidAdapter 在第一次启动时才会初始化，未初始化时默认为null
     * @return
     */
    @Override
    public boolean isStartedRec() {
        return mAndroidAdapter == null;
    }


    @Override
    public void OnItemClicked(FirstLevelInterfaceItem item) {
        startIntentActivity(this, new TechnologyWebViewContainerActivity(), "clickedBean", item);
    }
}
