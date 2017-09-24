package com.example.lilei.gank.base;

import com.example.lilei.gank.component.util.ACache;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/5.
 */

public abstract class BaseModel implements IBaseModel {

    @Override
    public ArrayList<FirstLevelInterfaceItem> getDataFromLocal(String key) {
        return (ArrayList<FirstLevelInterfaceItem >) ACache.getDefault().getAsObject(key);
    }

    @Override
    public void writeDataToLocal(String key, ArrayList<FirstLevelInterfaceItem> value) {
        ACache.getDefault().put(key, value);
    }
}
