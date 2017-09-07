package com.example.lilei.gank.modoules.ios;

import android.util.Log;

import com.example.lilei.gank.GankApplication;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.IBaseRecyclerModel;
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

public class IosPresenter implements IBaseRecyclerPresenter {

    private ArrayList<FirstLevelInterfaceItem> mIosArrayList;

    private IIosView iIosView;
    private IBaseRecyclerModel iIosModel;

    public IosPresenter(IIosView iIosView){
        this.iIosView = iIosView;
        iIosModel = new IosModel();
    }

    @Override
    public void initPage() {
        loadDataFromModel(1);
    }

    @Override
    public void loadDataFromModel(int page) {
        if (CommonUtil.isNetworkConnected(GankApplication.getContext()) || CommonUtil.isWifi(GankApplication.getContext())) {
            iIosModel.getDataFromWeb(page, new Observer<ArrayList<FirstLevelInterfaceItem>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(ArrayList<FirstLevelInterfaceItem> value) {
                    // TODO 判断是不是第一页
                    mIosArrayList = value;
                    Log.d("TAG", "onNext: "+value+"\n"+value.get(0).get_id());
                    if (iIosView.isStartedRec()){
//                        iAndroidModel.writeDataToLocal(GankApplication.cacheDir + "/"+"android", value);
                        iIosView.startRecyclerAdapter(mIosArrayList);
                    } else {
                        iIosView.changeDataRecyclerAdapter(mIosArrayList);
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
