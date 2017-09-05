package com.example.lilei.gank;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by lilei on 2017/9/5.
 */

public final class GankContext {
    private final static AtomicReference<GankApplication> aApplicaiton = new AtomicReference<>();
//    public static Context getApplicationContext(){
//        return Global.getApplicationContext();
//    }
    public static void setApplication(GankApplication application){


        if(application == null){
            throw new NullPointerException("application can not be null");
        }
        if(aApplicaiton.getAndSet(application) != null){
            throw new IllegalStateException("application can only be set once");
        }
    }

    public static GankApplication getApplication(){
        return aApplicaiton.get();
    }
}
