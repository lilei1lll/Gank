package com.example.lilei.gank.modoules.login;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseLogActivity;
import com.example.lilei.gank.component.util.ToastUtil;
import com.example.lilei.gank.modoules.main.GankActivity;

import java.io.IOException;

/**
 * Created by lilei on 2017/9/11.
 */

public class LoginActivity extends BaseLogActivity implements View.OnClickListener{

    private SurfaceView sfvBackground;
    private Button btLogin;
    private EditText etAccountNum;
    private EditText etPassword;
    private TextView tvForgetPassword, tvToRegister, tvAgreement;
    private MediaPlayer mediaPlayer;
    private int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (AVUser.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, GankActivity.class));
            LoginActivity.this.finish();
        }
        findId();
        initView();
    }

    private void findId() {
        sfvBackground = (SurfaceView) findViewById(R.id.login_surfaceView_background);
        btLogin = (Button) findViewById(R.id.login_loginButton);
        tvForgetPassword = (TextView) findViewById(R.id.login_forgetPassword);
        tvToRegister = (TextView) findViewById(R.id.login_toRegister);
        tvAgreement = (TextView) findViewById(R.id.login_agreement);
        etAccountNum = (EditText)findViewById(R.id.login_accountNum);
        etPassword = (EditText) findViewById(R.id.login_password);

        btLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);
        tvToRegister.setOnClickListener(this);
        tvAgreement.setOnClickListener(this);
    }

    private void initView() {
        mediaPlayer = new MediaPlayer();
        sfvBackground.getHolder().setKeepScreenOn(true);
        sfvBackground.getHolder().addCallback(new SurfaceViewLis());
    }

    //沉浸式状态栏
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    //软键盘
    @Override
    public int[] hideSoftByEditViewIds() {
        int[] ids = {R.id.login_accountNum, R.id.login_password};
        return ids;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_loginButton:
                attemptLogin();
                break;
            case R.id.login_agreement:
                startIntentActivity(this, new AgreementActivity());
                break;
            case R.id.login_toRegister:
                startIntentActivity(this, new RegisterActivity());
                LoginActivity.this.finish();
                break;
            case R.id.login_forgetPassword:
                ToastUtil.show("密码丢啦？那再注册一个吧，找回密码功能升级中");
                break;
        }

    }

    private void attemptLogin() {
        String username = etAccountNum.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty()){
            ToastUtil.show("请输入账号");
        } else if (password.isEmpty()){
            ToastUtil.show("请输入密码");
        } else {
            AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {
                    if (e == null) {
                        LoginActivity.this.finish();
                    } else {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private class SurfaceViewLis implements SurfaceHolder.Callback {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (position == 0) {
                try {
                    play();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }

    }

    public void play() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor fd = this.getAssets().openFd("mox.mp4");
        mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
                fd.getLength());
        mediaPlayer.setLooping(true);
        mediaPlayer.setDisplay(sfvBackground.getHolder());
        // 通过异步的方式装载媒体资源
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 装载完毕回调
                mediaPlayer.start();
            }
        });
    }


}
