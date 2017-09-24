package com.example.lilei.gank.modoules.webViewContainer;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.DeleteCallback;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.SaveCallback;
import com.example.lilei.gank.C;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;

import java.util.List;

/**
 * Created by lilei on 2017/9/11.
 */

public class TechnologyModel implements ITechnologyModel {

    private ITechnologyPresenter iTechnologyPresenter;

    public TechnologyModel(ITechnologyPresenter itp){
        this.iTechnologyPresenter = itp;
    }

    @Override
    public void isCollected(String _id) {
        AVQuery<AVObject> queryCollection = new AVQuery<AVObject>("Collection");
        queryCollection.whereEndsWith("collectedId", _id);
        queryCollection.include("owner");
        queryCollection.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null && list != null && list.size() != 0){
                    iTechnologyPresenter.setCollectionStatus(C.COLLECTED);
                } else {
                    iTechnologyPresenter.setCollectionStatus(C.UNCOLLECTED);
                }
            }
        });
    }

    @Override
    public void addCollectionToWeb(FirstLevelInterfaceItem item) {
        AVObject collection = new AVObject("Collection");
        collection.put("collectedId", item.get_id());
        collection.put("collectedDesc", item.getDesc());
        if (item.getImages() != null){
            collection.put("image", item.getImages().get(0));
        }
        collection.put("desc", item.getDesc());
        collection.put("publishedAt", item.getPublishedAt());
        collection.put("type", item.getType());
        collection.put("url", item.getUrl());
        collection.put("who", item.getWho());
        collection.put("owner", AVUser.getCurrentUser());
        collection.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    iTechnologyPresenter.setCollectionStatus(C.COLLECTED);
                } else {
                    iTechnologyPresenter.setCollectionStatus(C.DEFAULT);
                }
            }
        });
    }

    @Override
    public void removeCollectionToWeb(String _id) {
        AVQuery<AVObject> queryCollection = new AVQuery<AVObject>("Collection");
        queryCollection.whereEndsWith("collectedId", _id);
        queryCollection.include("owner");
        queryCollection.deleteAllInBackground(new DeleteCallback() {
            @Override
            public void done(AVException e) {
                if (e == null){
                    iTechnologyPresenter.setCollectionStatus(C.UNCOLLECTED);
                    Log.d("TAG", "done: "+C.UNCOLLECTED);
                } else {
                    iTechnologyPresenter.setCollectionStatus(C.DEFAULT);
                }
            }
        });
    }

}
