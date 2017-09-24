package com.example.lilei.gank.modoules.webViewContainer;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

/**
 * Created by lilei on 2017/9/11.
 */

public interface ITechnologyModel {
    void isCollected(String _id);
    void addCollectionToWeb(FirstLevelInterfaceItem item);
    void removeCollectionToWeb(String _id);
}
