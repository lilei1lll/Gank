package com.example.lilei.gank;

import android.app.Application;
import android.content.Context;

import com.example.lilei.gank.component.network.RetrofitNewSingleton;

/**
 * Created by lilei on 2017/9/4.
 */

public class GankApplication extends Application {

    private static Context mContext;
    public static String cacheDir = "";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        GankContext.setApplication(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        RetrofitNewSingleton.getInstance();
    }

    public static Context getContext() {
        return mContext;
    }
}
