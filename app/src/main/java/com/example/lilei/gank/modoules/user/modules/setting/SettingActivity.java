package com.example.lilei.gank.modoules.user.modules.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.component.util.FileSizeUtil;
import com.example.lilei.gank.component.util.MemoryCleanManager;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.modoules.user.IUserView;
import com.example.lilei.gank.modoules.user.UserFragment;
import com.example.lilei.gank.modoules.user.modules.aboutUs.RightActivity;
import com.example.lilei.gank.modoules.user.modules.setting.modules.feedback.FeedbackActivity;

/**
 * Created by lilei on 2017/8/14.
 *
 */

public class SettingActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageButton ibBack;
    private TextView tvFeedback;
    private TextView tvCacheClean;
    private TextView tvCacheSize;
    private TextView tvEditionUpdate;
    private TextView tvAboutUs;
    private TextView tvLogout;

    private IUserView iUserView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        initView();
        setStatusBarColor(R.color.main_toolbar);
        tvCacheSize.setText(FileSizeUtil.getAutoFileOrFilesSize("/data"));
    }

    private void initView(){
        iUserView = new UserFragment();

        ibBack = (ImageButton) findViewById(R.id.user_setting_back);
        tvFeedback = (TextView) findViewById(R.id.user_setting_feedback);
        tvCacheClean = (TextView) findViewById(R.id.user_setting_cacheClean);
        tvCacheSize = (TextView) findViewById(R.id.user_setting_cacheSize);
        tvEditionUpdate = (TextView) findViewById(R.id.user_setting_editionUpdate);
        tvAboutUs = (TextView) findViewById(R.id.user_setting_right);
        tvLogout = (TextView) findViewById(R.id.user_setting_logout);

        ibBack.setOnClickListener(this);
        tvFeedback.setOnClickListener(this);
        tvCacheClean.setOnClickListener(this);
        tvCacheSize.setOnClickListener(this);
        tvEditionUpdate.setOnClickListener(this);
        tvAboutUs.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_setting_back:
                finish();
                break;
            case R.id.user_setting_feedback:
                startIntentActivity(this, new FeedbackActivity());
                break;
            case R.id.user_setting_cacheClean:
            case R.id.user_setting_cacheSize:
                cleanCache();
                break;
            case R.id.user_setting_editionUpdate:
                // TODO: 版本检测
                ToastUtil.show("已是最新版");
                break;
            case R.id.user_setting_right:
                startIntentActivity(this, new RightActivity());
                break;
            case R.id.user_setting_logout:
                logout();
                break;
        }
    }



    private void cleanCache(){
        new  AlertDialog.Builder(this)
                .setTitle("确认" )
                .setMessage("真的要清缓存吗？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MemoryCleanManager.cleanSharedPreference(getApplicationContext());
                        tvCacheSize.setText("0KB");
                    }
                })
                .setNegativeButton("否" , null)
                .create()
                .show();
    }

    private void logout() {
        if (AVUser.getCurrentUser() != null){
            new AlertDialog.Builder(this)
                    .setTitle("")
                    .setMessage("确定退出此账号？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AVUser.getCurrentUser().logOut();
                            ToastUtil.show("已退出登录");
                            iUserView.changeNameStatus(C.UNLOGEDIN);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            ToastUtil.show(R.string.unlogin);
        }
    }
}
