package com.example.lilei.gank;

import android.app.Application;
import android.content.Context;

import com.example.lilei.gank.base.AppBlockCanaryContext;
import com.example.lilei.gank.component.network.RetrofitNewSingleton;
import com.example.lilei.gank.component.util.CrashHandler;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;

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
        CrashHandler.init(new CrashHandler(getApplicationContext()));  //crash的后台统计及处理
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
