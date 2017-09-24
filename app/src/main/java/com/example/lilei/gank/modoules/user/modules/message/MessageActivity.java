package com.example.lilei.gank.modoules.user.modules.message;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;

/**
 * Created by lilei on 2017/8/14.
 */

public class MessageActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageButton ivBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        setStatusBarColor(R.color.main_toolbar);
        initView();
    }

    private void initView(){
        ivBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);

        ivBack.setOnClickListener(this);
        tvTitle.setText(R.string.message);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_simple_back:
                finish();
                break;
        }
    }
}
