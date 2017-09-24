package com.example.lilei.gank.base;

/**
 * Created by lilei on 2017/9/4.
 */

public interface IBaseRecyclerPresenter {
    void initPage(int page);
    void loadDataFromWeb(int page);
    void refreshPage();
}
