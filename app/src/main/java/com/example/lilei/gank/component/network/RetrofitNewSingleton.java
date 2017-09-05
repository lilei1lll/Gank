package com.example.lilei.gank.component.network;

import android.content.Context;

import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by Hugo
 */
// TODO: 16/5/22 新封装好的网络库,推荐将网络请求统一写在该类,activity 直接调用方法即可 (讨论) --> 还需要封装好缓存的逻辑
public class RetrofitNewSingleton {

    private static ApiInterface apiService = null;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;


    private RetrofitNewSingleton() {
        initOkHttp();
        initRetrofit();
        apiService = retrofit.create(ApiInterface.class);
    }

    public static RetrofitNewSingleton getInstance() {
        return new RetrofitNewSingleton();
    }

    private static class SingletonHolder {
        private static final RetrofitNewSingleton INSTANCE = new RetrofitNewSingleton();
    }

    public static ApiInterface getApiService() {
        if (apiService == null) {
            throw new NullPointerException("get apiService must be called after init");
        }
        return apiService;
    }

    private static void initOkHttp() {
        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
        //设置retrofit拦截器，打印出请求体
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieJar mCookieJar = new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        };

        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor)
                .cookieJar(mCookieJar)
                .retryOnConnectionFailure(true)//断网自动重连
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void disposeFailureInfo(Throwable t, Context context) {
        if (context != null) {
            if (t.toString().contains("GaiException")
                    || t.toString().contains("SocketTimeoutException")
                    || t.toString().contains("UnknownHostException")) {
                ToastUtil.showLong(R.string.no_network);
            } else if (t.toString().contains("ConnectException")) {
                ToastUtil.showLong(R.string.fail_connet);
            } else if (t.getMessage().equals(C.UNLOGIN)) {
                ToastUtil.showLong(C.UNLOGIN);
//                UnLoginDispose.startLoginActivity(context);
            } else {
                ToastUtil.show(t.getMessage());
//                PLog.w(t.toString());

            }
        }
    }


    // 第一层界面
    public Observable<ArrayList<FirstLevelInterfaceItem>> getAndroid(int page) {
        return apiService.getAndroid(page).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult());
    }

    public Observable<ArrayList<FirstLevelInterfaceItem>> getIos(int page) {
        return apiService.getIos(page).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult());
    }

    public Observable<ArrayList<FirstLevelInterfaceItem>> getFrontend(int page) {
        return apiService.getFrontend(page).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult());
    }

    public Observable<ArrayList<FirstLevelInterfaceItem>> getWelfare(int page) {
        return apiService.getWelfare(page).compose(RxUtils.rxSchedulerHelper()).compose(RxUtils.handleResult());
    }
}
