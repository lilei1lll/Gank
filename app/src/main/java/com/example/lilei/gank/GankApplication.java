package com.example.lilei.gank;

import android.app.Application;
import android.content.Context;

import com.avos.avoscloud.AVOSCloud;
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
        AVOSCloud.initialize(this, "hGgy2GjgfjMzcoUKn3epVGut-gzGzoHsz", "iYhNcyjpppFdbq2lb8PsQQJR");
        AVOSCloud.setDebugLogEnabled(true);
        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */
        if (getApplicationContext().getExternalCacheDir() != null) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public static Context getContext() {
        return mContext;
    }
}
