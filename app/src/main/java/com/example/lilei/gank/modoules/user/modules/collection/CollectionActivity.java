package com.example.lilei.gank.modoules.user.modules.collection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.example.lilei.gank.R;
import com.example.lilei.gank.adapter.CollectionAdapter;
import com.example.lilei.gank.base.BaseSwipeBackActivity;

import java.util.List;


/**
 * Created by lilei on 2017/8/14.
 */

public class CollectionActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageButton ivBack;
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private CollectionAdapter collectionAdapter;
    private TextView tvNoCollection;
    private List<AVObject> mCollectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_collection);
        setStatusBarColor(R.color.main_toolbar);
        initView();
        statRecyclerView();
        getCollectionList();
    }


    private void initView(){
        ivBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.user_collection_recyclerView);
        tvNoCollection = (TextView) findViewById(R.id.user_collection_noCollection);
        ivBack.setOnClickListener(this);
        tvTitle.setText(R.string.collection);
    }

    private void statRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        collectionAdapter = new CollectionAdapter(this, mCollectionList);
        mRecyclerView.setAdapter(collectionAdapter);
    }

    private void getCollectionList() {
        AVQuery<AVObject> queryCollection = new AVQuery<AVObject>("Collection");
        queryCollection.whereEqualTo("owner", AVUser.getCurrentUser());
        queryCollection.include("owner");
        queryCollection.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (list != null && e == null){
                    tvNoCollection.setVisibility(View.GONE);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mCollectionList = list;
                    collectionAdapter.replaceList(mCollectionList);
                } else {
                    tvNoCollection.setVisibility(View.VISIBLE);
                    mRecyclerView.setVisibility(View.GONE);
                }
                Log.d("TAG", "done: "+list+"  "+e);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_simple_back:
                finish();
                break;
        }
    }


}
