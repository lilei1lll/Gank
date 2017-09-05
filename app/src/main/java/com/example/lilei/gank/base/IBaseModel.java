package com.example.lilei.gank.base;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * Created by lilei on 2017/9/4.
 */

public interface IBaseModel {

    void getDataFromWeb(int page, Observer<ArrayList<FirstLevelInterfaceItem>> observer);
    ArrayList<FirstLevelInterfaceItem> getDataFromLocal(String key);
    void writeDataToLocal(String key, ArrayList<FirstLevelInterfaceItem> value);

}
