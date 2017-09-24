package com.example.lilei.gank.modoules.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseActivity;
import com.example.lilei.gank.modoules.android.AndroidFragment;
import com.example.lilei.gank.modoules.frontend.FrontendFragment;
import com.example.lilei.gank.modoules.ios.IosFragment;
import com.example.lilei.gank.modoules.user.UserFragment;
import com.example.lilei.gank.modoules.welfare.WelfareFragment;

public class GankActivity extends BaseActivity implements View.OnClickListener{

    private static int SELECTED_FLAG = C.DEFAULT;

    private Toolbar mMainToolbar;
    private TextView tvTitle;
    private ImageView ivAndroid;
    private TextView tvAndroid;
    private ImageView ivIos;
    private TextView tvIos;
    private ImageView ivFrontend;
    private TextView tvFrontend;
    private ImageView ivWelfare;
    private TextView tvWelfare;
    private ImageView ivUser;
    private TextView tvUser;

    // Fragment管理器
    private android.support.v4.app.FragmentManager manager;
    android.support.v4.app.FragmentTransaction transaction;

    private AndroidFragment mAndroidFragment;
    private IosFragment mIosFragment;
    private FrontendFragment mFrontendFragment;
    private WelfareFragment mWelfareFragment;
    private UserFragment mUserFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        setStatusBarColor(R.color.main_toolbar);
        initView();
        changeItem(C.ANDROID);
    }

    private void initView() {
        mMainToolbar = (Toolbar) findViewById(R.id.gank_toolbar);
        tvTitle = (TextView)findViewById(R.id.toolbar_main_title);
        ivAndroid = (ImageView)findViewById(R.id.gank_android_imageView);
        tvAndroid = (TextView)findViewById(R.id.gank_android_TextView);
        ivIos = (ImageView)findViewById(R.id.gank_ios_ImageView);
        tvIos = (TextView)findViewById(R.id.gank_ios_TextView);
        ivFrontend = (ImageView)findViewById(R.id.gank_frontEnd_imageView);
        tvFrontend = (TextView)findViewById(R.id.gank_frontEnd_TextView);
        ivWelfare = (ImageView)findViewById(R.id.gank_welfare_imageView);
        tvWelfare = (TextView)findViewById(R.id.gank_welfare_textView);
        ivUser = (ImageView) findViewById(R.id.gank_user_imageView);
        tvUser = (TextView) findViewById(R.id.gank_user_textView);

        ivAndroid.setOnClickListener(this);
        tvAndroid.setOnClickListener(this);
        ivIos.setOnClickListener(this);
        tvIos.setOnClickListener(this);
        ivFrontend.setOnClickListener(this);
        tvFrontend.setOnClickListener(this);
        ivWelfare.setOnClickListener(this);
        tvWelfare.setOnClickListener(this);
        ivUser.setOnClickListener(this);
        tvUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gank_android_imageView:
                changeItem(C.ANDROID);
                break;
            case R.id.gank_android_TextView:
                changeItem(C.ANDROID);
                break;
            case R.id.gank_ios_ImageView:
                changeItem(C.IOS);
                break;
            case R.id.gank_ios_TextView:
                changeItem(C.IOS);
                break;
            case R.id.gank_frontEnd_imageView:
                changeItem(C.FRONTEND);
                break;
            case R.id.gank_frontEnd_TextView:
                changeItem(C.FRONTEND);
                break;
            case R.id.gank_welfare_imageView:
                changeItem(C.WELFARE);
                break;
            case R.id.gank_welfare_textView:
                changeItem(C.WELFARE);
                break;
            case R.id.gank_user_imageView:
                changeItem(C.USER);
                break;
            case R.id.gank_user_textView:
                changeItem(C.USER);
                break;
        }
    }

    private void changeItem(int chosenItem){
        if (chosenItem != SELECTED_FLAG){

            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();

            switch (SELECTED_FLAG){
                case C.ANDROID:
                    ivAndroid.setImageResource(R.mipmap.android_unselected);
                    tvAndroid.setTextColor(getResources().getColor(R.color.unselected));
                    break;
                case C.IOS:
                    ivIos.setImageResource(R.mipmap.ios_unselected);
                    tvIos.setTextColor(getResources().getColor(R.color.unselected));
                    break;
                case C.FRONTEND:
                    ivFrontend.setImageResource(R.mipmap.frontend_unselected);
                    tvFrontend.setTextColor(getResources().getColor(R.color.unselected));
                    break;
                case C.WELFARE:
                    ivWelfare.setImageResource(R.mipmap.welfare_unselected);
                    tvWelfare.setTextColor(getResources().getColor(R.color.unselected));
                    break;
                case C.USER:
                    ivUser.setImageResource(R.mipmap.user_unselected);
                    tvUser.setTextColor(getResources().getColor(R.color.unselected));
                    break;
            }

            switch (chosenItem){
                case C.ANDROID:
                    ivAndroid.setImageResource(R.mipmap.android_selected);
                    tvAndroid.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.ANDROID;
                    mAndroidFragment = new AndroidFragment();
                    transaction.replace(R.id.gank_fragment, mAndroidFragment);
                    mMainToolbar.setVisibility(View.VISIBLE);
                    tvTitle.setText(R.string.androidTech);
                    break;
                case C.IOS:
                    ivIos.setImageResource(R.mipmap.ios_selected);
                    tvIos.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.IOS;
                    mIosFragment = new IosFragment();
                    transaction.replace(R.id.gank_fragment, mIosFragment);
                    mMainToolbar.setVisibility(View.VISIBLE);
                    tvTitle.setText(R.string.iosTech);
                    break;
                case C.FRONTEND:
                    ivFrontend.setImageResource(R.mipmap.frontend_selected);
                    tvFrontend.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.FRONTEND;
                    mFrontendFragment = new FrontendFragment();
                    transaction.replace(R.id.gank_fragment, mFrontendFragment);
                    mMainToolbar.setVisibility(View.VISIBLE);
                    tvTitle.setText(R.string.frontEnd);
                    break;
                case C.WELFARE:
                    ivWelfare.setImageResource(R.mipmap.welfare_selected);
                    tvWelfare.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.WELFARE;
                    mWelfareFragment = new WelfareFragment();
                    transaction.replace(R.id.gank_fragment, mWelfareFragment);
                    mMainToolbar.setVisibility(View.VISIBLE);
                    tvTitle.setText(R.string.welfare);
                    break;
                case C.USER:
                    ivUser.setImageResource(R.mipmap.user_selectd);
                    tvUser.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.USER;
                    mUserFragment = new UserFragment();
                    transaction.replace(R.id.gank_fragment, mUserFragment);
                    mMainToolbar.setVisibility(View.GONE);
                    tvTitle.setText(R.string.personalCenter);
                    break;
            }
            transaction.commit();
        }
    }
}
