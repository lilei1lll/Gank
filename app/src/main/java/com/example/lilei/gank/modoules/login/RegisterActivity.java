package com.example.lilei.gank.modoules.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseSwipeBackActivity;
import com.example.lilei.gank.modoules.user.IUserView;
import com.example.lilei.gank.modoules.user.UserFragment;

/**
 * Created by lilei on 2017/9/11.
 */

public class RegisterActivity extends BaseSwipeBackActivity {

    IUserView iUserView;

    private ImageButton ibBack;
    private TextView tvTitle;
    private AutoCompleteTextView tvUsername;
    private EditText etPassword1;
    private EditText etPassword2;
    private Button btRegisterAndLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setStatusBarColor(R.color.main_toolbar);
        initView();
    }


    private void initView() {
        iUserView = new UserFragment();

        ibBack = (ImageButton) findViewById(R.id.toolbar_simple_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_simple_title);
        tvUsername = (AutoCompleteTextView) findViewById(R.id.register_username);
        etPassword1 = (EditText) findViewById(R.id.register_password1);
        etPassword2 = (EditText) findViewById(R.id.register_password2);
        btRegisterAndLogin = (Button) findViewById(R.id.register_registerButton);

        tvTitle.setText(R.string.register);
        ibBack.setOnClickListener(l -> finish());
        btRegisterAndLogin.setOnClickListener(l -> attemptRegister());
    }

    private void attemptRegister() {

        tvUsername.setError(null);
        etPassword1.setError(null);

        String username = tvUsername.getText().toString();
        String password1 = etPassword1.getText().toString();
        String password2 = etPassword2.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!password1.isEmpty() && !password2.isEmpty() && password1.equals(password2)){
            etPassword2.setError(getString(R.string.error_not_equals));
            focusView = etPassword2;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password1) && !isPasswordValid(password1)) {
            etPassword1.setError(getString(R.string.error_invalid_password));
            focusView = etPassword1;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password2) && !isPasswordValid(password2)){
            etPassword2.setError(getString(R.string.error_invalid_password));
            focusView = etPassword2;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            tvUsername.setError(getString(R.string.error_field_required));
            focusView = tvUsername;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            AVUser user = new AVUser();// 新建 AVUser 对象实例
            user.setUsername(username);// 设置用户名
            user.setPassword(password1);// 设置密码
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        iUserView.changeNameStatus(C.LOGEDIN);
//                        Intent intent = new Intent();
//                        intent.setClass(RegisterActivity.this, GankActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                        startActivity(new Intent(RegisterActivity.this, GankActivity.class));
                        RegisterActivity.this.finish();
                    } else {
                        // 失败的原因可能有多种，常见的是用户名已经存在。
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

}
