package com.example.lilei.gank.modoules.webViewContainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseActivity;
import com.example.lilei.gank.component.util.CommonUtil;

/**
 * Created by lilei on 2017/9/6.
 */

public class WebViewContainerActivity extends BaseActivity implements View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivCollection;
    private WebView mWebView;
    private LinearLayout mLinearLayout;

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
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.toolbar_webview_container_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_webview_container_title);
        ivCollection = (ImageView) findViewById(R.id.toolbar_webview_container_collection);
        mLinearLayout = (LinearLayout) findViewById(R.id.webView_container_linearLayout);

        ivBack.setOnClickListener(this);
        ivCollection.setOnClickListener(this);

        tvTitle.setText(CommonUtil.safeText(mTitleText));
        // 动态生成webView
        mWebView = new WebView(getApplicationContext());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mUrl);
        mLinearLayout.addView(mWebView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.removeAllViews();
        mWebView.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_webview_container_back:
                finish();
                break;
            case R.id.toolbar_webview_container_collection:
                break;
        }
    }
}
