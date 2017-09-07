package com.example.lilei.gank.base;


import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.android.BuildConfig;


/**
 * Created by Soully on 17/7/25
 * Study on 'http://www.jianshu.com/p/5d9eca9c343a'
 */
public class AppBlockCanaryContext extends BlockCanaryContext {

    //设置卡顿判断的阙值
    @Override
    public int getConfigBlockThreshold() {
        return 500;
    }

    //是否需要显示卡顿的信息
    @Override
    public boolean isNeedDisplay() {
        return BuildConfig.DEBUG;
    }

    //设置log保存在sd卡的目录位置
    @Override
    public String getLogPath() {
        return "/blockcanary/performance";
    }
}