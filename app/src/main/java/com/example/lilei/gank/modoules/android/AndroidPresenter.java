package com.example.lilei.gank.modoules.android;

import com.example.lilei.gank.GankApplication;
import com.example.lilei.gank.base.IBaseRecyclerModel;
import com.example.lilei.gank.base.IBaseRecyclerPresenter;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/5.
 */

public class AndroidPresenter implements IBaseRecyclerPresenter {

    // 判断technologyArrayList是否为空交给RecyclerView的Adapter
    private ArrayList<FirstLevelInterfaceItem> technologyArrayList;

    private IAndroidView iAndroidView;
    private IBaseRecyclerModel iAndroidModel;

    public AndroidPresenter(IAndroidView iAndroidView){
        this.iAndroidView = iAndroidView;
        this.iAndroidModel = new AndroidModel();
    }

    @Override
    public void initPage() {
         technologyArrayList = iAndroidModel.getDataFromLocal(GankApplication.cacheDir + "/"+"android");
    }

    @Override
    public void loadDataFromModel(int page) {

    }

    @Override
    public void refreshPage() {

    }
}
