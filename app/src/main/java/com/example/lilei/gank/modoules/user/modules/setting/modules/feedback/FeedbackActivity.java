package com.example.lilei.gank.modoules.user.modules.setting.modules.feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.component.util.RxBus;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.event.MessageEvent;

/**
 * Created by lilei on 2017/8/16.
 */

public class FeedbackActivity extends BaseSwipeBackActivity implements View.OnClickListener{

    private ImageButton ibBack;
    private TextView tvTitle;
    private EditText etFeedbackTitle;
    private EditText etFeedbackContent;
    private Button btCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting_feedback);
        initView();
        setStatusBarColor(R.color.main_toolbar);
    }

    private void initView(){
        ibBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);
        etFeedbackTitle = (EditText) findViewById(R.id.user_setting_feedback_title);
        etFeedbackContent = (EditText) findViewById(R.id.user_setting_feedback_contactInformation);
        btCommit = (Button) findViewById(R.id.user_setting_feedback_commit);

        ibBack.setOnClickListener(this);
        btCommit.setOnClickListener(this);
        tvTitle.setText(R.string.feedback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_simple_back:
                finish();
                break;
            case R.id.user_setting_feedback_commit:
                if (isEmpty(etFeedbackTitle)){
                    ToastUtil.show("请输入标题");
                } else if (isEmpty(etFeedbackContent)) {
                    ToastUtil.show("请输入内容");
                } else if (AVUser.getCurrentUser() == null){
                    ToastUtil.show("请先登录再反馈");
                } else {
                    RxBus.getDefault().post(new MessageEvent(1));
                    AVObject product = new AVObject("Feedback");
                    product.put("feedbackTitle", etFeedbackTitle.getText().toString());
                    product.put("feedbackContent", etFeedbackContent.getText().toString());
                    product.put("owner", AVUser.getCurrentUser());
                    product.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e != null) {
                                ToastUtil.show( e.getMessage());
                            } else {
                                ToastUtil.show("感谢您的提交，我们将尽快处理");                            }
                        }
                    });
                    etFeedbackTitle.setText("");
                    etFeedbackContent.setText("");
                }
                break;
        }
    }

    private boolean isEmpty(EditText et) {
        return "".equals(et.getText().toString());
    }
}
