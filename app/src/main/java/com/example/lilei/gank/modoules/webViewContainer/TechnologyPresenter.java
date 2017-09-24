package com.example.lilei.gank.modoules.webViewContainer;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

/**
 * Created by lilei on 2017/9/11.
 */

public class TechnologyPresenter implements ITechnologyPresenter {

    ITechnologyView iTechnologyView;
    ITechnologyModel iTechnologyModel;

    public TechnologyPresenter(ITechnologyView iTechnologyView){
        this.iTechnologyView = iTechnologyView;
        iTechnologyModel = new TechnologyModel(this);
    }

    @Override
    public void isCollection(String _id) {
        iTechnologyModel.isCollected(_id);
    }

    @Override
    public void addCollection(FirstLevelInterfaceItem item) {
        iTechnologyModel.addCollectionToWeb(item);
    }

    @Override
    public void removeCollection(String _id) {
        iTechnologyModel.removeCollectionToWeb(_id);
    }

    /**
     * 解决cleanCloud异步问题
     * @param aimStatus
     */
    @Override
    public void setCollectionStatus(int aimStatus) {
        iTechnologyView.changeCollectionImage(aimStatus);
    }


}
