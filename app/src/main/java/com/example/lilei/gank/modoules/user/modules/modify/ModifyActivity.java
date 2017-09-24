package com.example.lilei.gank.modoules.user.modules.modify;

import android.os.Bundle;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;

/**
 * Created by lilei on 2017/8/15.
 * 此类未使用  留为拓展功能  -》 修改信息界面
 *
 */

public class ModifyActivity extends BaseSwipeBackActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modify);
        setStatusBarColor(R.color.main_toolbar);
    }
}
