package com.example.lilei.gank.component.network;

import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.entity.Result;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lilei on 2017/9/4.
 */

public interface ApiInterface {

    String HOST="http://gank.io/api/data/";

//    android干货
//    @FormUrlEncoded 只能用于post之类的请求
    @GET("Android/10/{page}")
    Observable<Result<ArrayList<FirstLevelInterfaceItem>>> getAndroid(@Path("page") int page);


//    ios干货
    @GET("iOS/10/{page}")
    Observable<Result<ArrayList<FirstLevelInterfaceItem>>> getIos(@Path("page") int page);


//    前端干货
    @GET("前端/10/{page}")
    Observable<Result<ArrayList<FirstLevelInterfaceItem>>> getFrontend(@Path("page") int page);


//    福利
    @GET("福利/10/{page}")
    Observable<Result<ArrayList<FirstLevelInterfaceItem>>> getWelfare(@Path("page") int page);

}
