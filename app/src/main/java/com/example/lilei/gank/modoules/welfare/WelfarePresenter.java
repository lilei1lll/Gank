package com.example.lilei.gank.modoules.welfare;

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
 * Created by lilei on 2017/9/7.
 */

public class WelfarePresenter implements IBaseRecyclerPresenter {

    private ArrayList<FirstLevelInterfaceItem> mWelfareArrayList;

    private IWelfareView iWelfareView;
    private IBaseModel iWelfareModel;

    public WelfarePresenter(IWelfareView iWelfareView){
        this.iWelfareView = iWelfareView;
        iWelfareModel = new WelfareModel();
    }

    @Override
    public void initPage(int page) {
        //        mWelfareArrayList = iWelfareModel.getDataFromLocal(GankApplication.cacheDir + "/"+"welfare");
//        iWelfareView.startRecyclerAdapter(mWelfareArrayList);
        loadDataFromWeb(page);
    }

    @Override
    public void loadDataFromWeb(int page) {
        if (CommonUtil.isNetworkConnected(GankApplication.getContext()) || CommonUtil.isWifi(GankApplication.getContext())) {
            iWelfareModel.getDataFromWeb(page, new Observer<ArrayList<FirstLevelInterfaceItem>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ArrayList<FirstLevelInterfaceItem> value) {
                    // TODO 判断是不是第一页
                    mWelfareArrayList = value;
                    Log.d("TAG", "onNext: "+value+"\n"+value.get(0).get_id());
                    if (iWelfareView.isStartedRec()){
//                        iAndroidModel.writeDataToLocal("android", value);
                        iWelfareView.startRecyclerAdapter(mWelfareArrayList);
                    } else {
                        iWelfareView.changeDataRecyclerAdapter(mWelfareArrayList);
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

    @Override
    public void refreshPage() {

    }
}
