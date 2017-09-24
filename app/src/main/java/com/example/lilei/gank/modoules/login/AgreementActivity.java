package com.example.lilei.gank.modoules.login;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;

/**
 * Created by lilei on 2017/9/11.
 */

public class AgreementActivity extends BaseSwipeBackActivity{

    private ImageView ivBack;
    private TextView tvTitle;
    private WebView mWebView;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_agreement);
        initView();
        // 动态生成webView
        mWebView = new WebView(getApplicationContext());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            // 屏蔽超链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.loadUrl("https://github.com/lilei1lll/Gank/blob/master/LICENSE");
        mLinearLayout.addView(mWebView);
    }

    private void initView() {
        ivBack = (ImageView) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);
        mLinearLayout = (LinearLayout) findViewById(R.id.login_agreement_webView_container);

        tvTitle.setText(R.string.agreement);
        ivBack.setOnClickListener(l -> finish());
    }

}
