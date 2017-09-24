package com.example.lilei.gank.modoules.android;

import android.util.Log;

import com.example.lilei.gank.GankApplication;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.IBaseModel;
import com.example.lilei.gank.base.IBaseRecyclerPresenter;
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lilei on 2017/9/5.
 */

public class AndroidPresenter implements IBaseRecyclerPresenter {

    // 判断technologyArrayList是否为空交给RecyclerView的Adapter
    private ArrayList<FirstLevelInterfaceItem> mAndroidArrayList;

    private IAndroidView iAndroidView;
    private IBaseModel iAndroidModel;

    public AndroidPresenter(IAndroidView iAndroidView){
        this.iAndroidView = iAndroidView;
        this.iAndroidModel = new AndroidModel();
    }

    @Override
    public void initPage(int page) {
//        mAndroidArrayList = iAndroidModel.getDataFromLocal("android"+page); //默认只缓存第一页
//        iAndroidView.startRecyclerAdapter(mAndroidArrayList);
        loadDataFromWeb(page);
    }

    @Override
    public void loadDataFromWeb(int page) {
        if (CommonUtil.isNetworkConnected(GankApplication.getContext()) || CommonUtil.isWifi(GankApplication.getContext())) {
            iAndroidModel.getDataFromWeb(page, new Observer<ArrayList<FirstLevelInterfaceItem>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ArrayList<FirstLevelInterfaceItem> value) {
                    // TODO 判断是不是第一页
                    mAndroidArrayList = value;
                    Log.d("TAG", "onNext: "+value+"\n"+value.get(0).get_id());
                    if (iAndroidView.isStartedRec()){
//                        iAndroidModel.writeDataToLocal("android"+page, value);
                        iAndroidView.startRecyclerAdapter(mAndroidArrayList);
                    } else {
                        iAndroidView.changeDataRecyclerAdapter(mAndroidArrayList);
                    }
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        } else {
            ToastUtil.show(R.string.no_network);
        }
    }

    //TODO 刷新接口的实现方法(记得添加一个stopRefresh（）)
    @Override
    public void refreshPage() {

    }
}
