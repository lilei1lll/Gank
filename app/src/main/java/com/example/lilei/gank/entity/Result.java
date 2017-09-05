package com.example.lilei.gank.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lilei on 2017/9/4.
 * 所有请求返回的结果
 */
public class Result<T> {
    @SerializedName("error")
    public boolean error;
    @SerializedName("results")
    public T data;
}
