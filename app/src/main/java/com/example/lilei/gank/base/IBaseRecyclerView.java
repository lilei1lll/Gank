package com.example.lilei.gank.base;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.ArrayList;

/**
 * Created by lilei on 2017/9/5.
 */

public interface IBaseRecyclerView {
    void startRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items);
    void addDataToRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items);
    void replaceDataRecyclerAdapter(ArrayList<FirstLevelInterfaceItem> items);
}
