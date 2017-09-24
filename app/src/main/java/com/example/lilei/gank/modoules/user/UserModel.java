package com.example.lilei.gank.modoules.user;

import com.example.lilei.gank.base.BaseModel;
import com.example.lilei.gank.base.IBaseModel;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by lilei on 2017/9/11.
 */

public class UserModel extends BaseModel implements IBaseModel {
    @Override
    public void getDataFromWeb(int page, Observer<ArrayList<FirstLevelInterfaceItem>> observer) {

    }
}
