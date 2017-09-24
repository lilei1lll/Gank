package com.example.lilei.gank.modoules.user.modules.published;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;

/**
 * Created by lilei on 2017/9/11.
 */

public class PublishedFrontend extends BaseSwipeBackActivity implements View.OnClickListener {
    private ImageButton ibBack;
    private TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(R.color.main_toolbar);
        setContentView(R.layout.activity_user_published_frondend);
        ibBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);

        ibBack.setOnClickListener(l -> finish());
        tvTitle.setText("发布过的前端技术");
    }

    @Override
    public void onClick(View v) {

    }
}
