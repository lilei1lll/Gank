package com.example.lilei.gank.component.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.example.lilei.gank.GankApplication;


/**
 * Created by lilei on 2017/8/14.
 */

public class ToastUtil {

    public static void show(String content) {
        Toast.makeText(GankApplication.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    public static void show(@StringRes int res) {
        Toast.makeText(GankApplication.getContext(), res, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String content) {
        Toast.makeText(GankApplication.getContext(), content, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@StringRes int res) {
        Toast.makeText(GankApplication.getContext(), res, Toast.LENGTH_SHORT).show();
    }
}

