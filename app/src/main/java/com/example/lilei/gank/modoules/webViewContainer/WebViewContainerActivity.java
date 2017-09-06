package com.example.lilei.gank.modoules.webViewContainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.component.util.ToastUtil;

/**
 * Created by lilei on 2017/9/6.
 */

public class WebViewContainerActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivCollection;
    private WebView mWebView;
    private LinearLayout mLinearLayout;
    private ProgressBar mProgressBar;

    private String mTitleText;
    private String mUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_container);
        Intent intent = getIntent();
        mTitleText = intent.getStringExtra("webViewTitle");
        mUrl = intent.getStringExtra("webViewUrl");

        initView();

        // 动态生成webView
        mWebView = new WebView(getApplicationContext());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mUrl);
        mLinearLayout.addView(mWebView);
        setProgressbar(mWebView);
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.toolbar_webview_container_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_webview_container_title);
        ivCollection = (ImageView) findViewById(R.id.toolbar_webview_container_collection);
        mLinearLayout = (LinearLayout) findViewById(R.id.webView_container_linearLayout);
        mProgressBar = (ProgressBar) findViewById(R.id.webView_container_progressBar);

        ivBack.setOnClickListener(this);
        ivCollection.setOnClickListener(this);

        tvTitle.setText(CommonUtil.safeText(mTitleText));
    }

    private void setProgressbar(WebView webView){
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress==100){
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                    Log.d("TAG", "onProgressChanged: 加载完网页");
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);   //开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);      //设置进度值
                    Log.d("TAG", "onProgressChanged: 加载中"+newProgress);
                }

            }
        });
    }


    private void removeWebView(){
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeWebView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_webview_container_back:
                removeWebView();
                finish();
                break;
            case R.id.toolbar_webview_container_collection:
                ToastUtil.show("收藏功能完善中...");
                break;
        }
    }
}
