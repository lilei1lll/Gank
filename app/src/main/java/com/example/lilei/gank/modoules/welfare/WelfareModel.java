package com.example.lilei.gank.modoules.welfare;

import com.example.lilei.gank.base.BaseModel;
import com.example.lilei.gank.base.IBaseModel;
import com.example.lilei.gank.component.network.RetrofitNewSingleton;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by lilei on 2017/9/7.
 */

public class WelfareModel extends BaseModel implements IBaseModel {

    @Override
    public void getDataFromWeb(int page, Observer<ArrayList<FirstLevelInterfaceItem>> observer) {
        RetrofitNewSingleton.getInstance().getWelfare(page).subscribe(observer);
    }
}
