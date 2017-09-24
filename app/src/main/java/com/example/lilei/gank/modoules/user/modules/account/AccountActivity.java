package com.example.lilei.gank.modoules.user.modules.account;

import android.os.Bundle;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;


/**
 * Created by lilei on 2017/8/14.
 *
 * 此类未使用  留为拓展功能  -》 账户信息界面
 */

public class AccountActivity extends BaseSwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        setStatusBarColor(R.color.main_toolbar);
    }
}
