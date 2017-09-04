package com.example.lilei.gank;

import android.app.Application;
import android.content.Context;

/**
 * Created by lilei on 2017/9/4.
 */

public class GankApplication extends Application {

    private static Context mContext;
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
