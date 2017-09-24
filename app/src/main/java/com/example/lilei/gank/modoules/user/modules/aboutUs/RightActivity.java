package com.example.lilei.gank.modoules.user.modules.aboutUs;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.component.util.ToastUtil;


/**
 * Created by lilei on 2017/8/15.
 */

public class RightActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageButton ivBack;
    private TextView tvTitle;
    private TextView tvShareToFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_right);
        setStatusBarColor(R.color.main_toolbar);
        initView();
    }

    private void initView(){
        ivBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);
        tvShareToFriend = (TextView) findViewById(R.id.user_right_recommend);

        tvTitle.setText(R.string.aboutMe);
        ivBack.setOnClickListener(this);
        tvShareToFriend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_simple_back:
                finish();
                break;
            case R.id.user_right_recommend:
                ToastUtil.show("分享功能完善中...");
                break;
        }
    }
}
