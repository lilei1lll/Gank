package com.example.lilei.gank.modoules.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseActivity;
import com.example.lilei.gank.modoules.android.AndroidFragment;
import com.example.lilei.gank.modoules.frontend.FrontEndFragment;
import com.example.lilei.gank.modoules.ios.IosFragment;
import com.example.lilei.gank.modoules.welfare.WelfareFragment;

public class GankActivity extends BaseActivity implements View.OnClickListener{

    private static int SELECTED_FLAG = C.DEFAULT;

    private TextView tvTitle;
    private ImageView ivAndroid;
    private TextView tvAndroid;
    private ImageView ivIos;
    private TextView tvIos;
    private ImageView ivFrontend;
    private TextView tvFrontend;
    private ImageView ivWelfare;
    private TextView tvWelfare;

    // Fragment管理器
    private android.support.v4.app.FragmentManager manager;
    android.support.v4.app.FragmentTransaction transaction;

    private AndroidFragment mAndroidFragment;
    private IosFragment mIosFragment;
    private FrontEndFragment mFrontEndFragment;
    private WelfareFragment mWelfareFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        setStatusBarColor(R.color.main_toolbar);
        initView();
        changeItem(C.ANDROID);
    }

    private void initView() {
        tvTitle = (TextView)findViewById(R.id.gank_toolbar_title);
        ivAndroid = (ImageView)findViewById(R.id.gank_android_imageView);
        tvAndroid = (TextView)findViewById(R.id.gank_android_TextView);
        ivIos = (ImageView)findViewById(R.id.gank_ios_ImageView);
        tvIos = (TextView)findViewById(R.id.gank_ios_TextView);
        ivFrontend = (ImageView)findViewById(R.id.gank_frontEnd_imageView);
        tvFrontend = (TextView)findViewById(R.id.gank_frontEnd_TextView);
        ivWelfare = (ImageView)findViewById(R.id.gank_welfare_imageView);
        tvWelfare = (TextView)findViewById(R.id.gank_welfare_textView);

        ivAndroid.setOnClickListener(this);
        tvAndroid.setOnClickListener(this);
        ivIos.setOnClickListener(this);
        tvIos.setOnClickListener(this);
        ivFrontend.setOnClickListener(this);
        tvFrontend.setOnClickListener(this);
        ivWelfare.setOnClickListener(this);
        tvWelfare.setOnClickListener(this);
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
            }

            switch (chosenItem){
                case C.ANDROID:
                    ivAndroid.setImageResource(R.mipmap.android_selected);
                    tvAndroid.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.ANDROID;
                    mAndroidFragment = new AndroidFragment();
                    transaction.replace(R.id.gank_fragment, mAndroidFragment);
                    tvTitle.setText("Android 技术");
                    break;
                case C.IOS:
                    ivIos.setImageResource(R.mipmap.ios_selected);
                    tvIos.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.IOS;
                    mIosFragment = new IosFragment();
                    transaction.replace(R.id.gank_fragment, mIosFragment);
                    tvTitle.setText("IOS 技术");
                    break;
                case C.FRONTEND:
                    ivFrontend.setImageResource(R.mipmap.frontend_selected);
                    tvFrontend.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.FRONTEND;
                    mFrontEndFragment = new FrontEndFragment();
                    transaction.replace(R.id.gank_fragment, mFrontEndFragment);
                    tvTitle.setText("前端");
                    break;
                case C.WELFARE:
                    ivWelfare.setImageResource(R.mipmap.welfare_selected);
                    tvWelfare.setTextColor(getResources().getColor(R.color.selected));
                    SELECTED_FLAG = C.WELFARE;
                    mWelfareFragment = new WelfareFragment();
                    transaction.replace(R.id.gank_fragment, mWelfareFragment);
                    tvTitle.setText("福利");
                    break;
            }

            transaction.commit();
        }
    }
}
