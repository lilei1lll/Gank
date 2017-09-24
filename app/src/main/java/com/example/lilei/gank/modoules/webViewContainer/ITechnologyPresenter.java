package com.example.lilei.gank.modoules.webViewContainer;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

/**
 * Created by lilei on 2017/9/11.
 */

public interface ITechnologyPresenter {
    void isCollection(String _id);
    void addCollection(FirstLevelInterfaceItem item);
    void removeCollection(String _id);
    void setCollectionStatus(int aimStatus);
}
