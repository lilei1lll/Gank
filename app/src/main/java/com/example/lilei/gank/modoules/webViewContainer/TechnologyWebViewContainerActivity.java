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

import com.avos.avoscloud.AVUser;
import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.component.util.CommonUtil;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.entity.FirstLevelInterfaceItem;
import com.example.lilei.gank.modoules.login.LoginActivity;

/**
 * Created by lilei on 2017/9/6.
 */

public class TechnologyWebViewContainerActivity extends BaseSwipeBackActivity implements ITechnologyView, View.OnClickListener{

    private ImageView ivBack;
    private TextView tvTitle;
    private ImageView ivCollection;
    private WebView mWebView;
    private LinearLayout mLinearLayout;
    private ProgressBar mProgressBar;

    private String mTitleText;
    private String mUrl;

    private FirstLevelInterfaceItem mClickedBean;
    private ITechnologyPresenter iTechnologyPresenter;

    private boolean isCollected = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology_webview_container);
        Intent intent = getIntent();
        mClickedBean = (FirstLevelInterfaceItem)intent.getSerializableExtra("clickedBean");
        mTitleText = mClickedBean.getDesc();
        mUrl = mClickedBean.getUrl();

        initView();
        iTechnologyPresenter.isCollection(mClickedBean.get_id());

        // 动态生成webView
        mWebView = new WebView(getApplicationContext());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.loadUrl(mUrl);
        mLinearLayout.addView(mWebView);
        setProgressbar(mWebView);
    }

    private void initView() {
        iTechnologyPresenter = new TechnologyPresenter(this);

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
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);   //开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);      //设置进度值
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
                if (AVUser.getCurrentUser() == null){
                    startIntentActivity(this, new LoginActivity());
                } else if (!isCollected){
                    iTechnologyPresenter.addCollection(mClickedBean);
                } else {
                    iTechnologyPresenter.removeCollection(mClickedBean.get_id());
                }
                break;
        }
    }


    @Override
    public void changeCollectionImage(int aimImage) {
        switch (aimImage){
            case C.COLLECTED:
                ivCollection.setImageResource(R.mipmap.collection_selected);
                isCollected = true;
                Log.d("TAG", "changeCollectionImage: 1");
                break;
            case C.UNCOLLECTED:
                ivCollection.setImageResource(R.mipmap.collection_unselected);
                isCollected = false;
                Log.d("TAG", "changeCollectionImage: 2");
                break;
            default:
                ToastUtil.show(R.string.tryAgain);
                break;
        }
    }

}
