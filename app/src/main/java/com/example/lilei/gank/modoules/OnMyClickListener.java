package com.example.lilei.gank.modoules;

/**
 * Created by lilei on 2017/9/5.
 */

public interface OnMyClickListener {
    /**
     * 此接口是多次使用，是全局点击事件的接口
     *
     * @param aim 目标
     * @param cont 内容
     */
    void OnItemClicked(String aim, String cont);
}
