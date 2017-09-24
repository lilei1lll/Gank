package com.example.lilei.gank.modoules.frontend;

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

public class FrontendPresenter implements IBaseRecyclerPresenter {

    private ArrayList<FirstLevelInterfaceItem> mFrontendArrayList;

    private IFrontendView iFrontendView;
    private IBaseModel iFrontendModel;

    public FrontendPresenter(IFrontendView iFrontendView){
        this.iFrontendView = iFrontendView;
        iFrontendModel = new FrontendModel();
    }


    @Override
    public void initPage(int page) {
        loadDataFromWeb(page);
    }

    @Override
    public void loadDataFromWeb(int page) {
        if (CommonUtil.isNetworkConnected(GankApplication.getContext()) || CommonUtil.isWifi(GankApplication.getContext())) {
            iFrontendModel.getDataFromWeb(page, new Observer<ArrayList<FirstLevelInterfaceItem>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ArrayList<FirstLevelInterfaceItem> value) {
                    // TODO 判断是不是第一页
                    mFrontendArrayList = value;
                    Log.d("TAG", "onNext: "+value+"\n"+value.get(0).get_id());
                    if (iFrontendView.isStartedRec()){
//                        iAndroidModel.writeDataToLocal(GankApplication.cacheDir + "/"+"android", value);
                        iFrontendView.startRecyclerAdapter(mFrontendArrayList);
                    } else {
                        iFrontendView.changeDataRecyclerAdapter(mFrontendArrayList);
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
