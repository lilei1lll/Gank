package com.example.lilei.gank.modoules.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVUser;
import com.example.lilei.gank.C;
import com.example.lilei.gank.R;
import com.example.lilei.gank.base.BaseFragment;
import com.example.lilei.gank.modoules.login.LoginActivity;
import com.example.lilei.gank.modoules.user.modules.aboutUs.RightActivity;
import com.example.lilei.gank.modoules.user.modules.collection.CollectionActivity;
import com.example.lilei.gank.modoules.user.modules.message.MessageActivity;
import com.example.lilei.gank.modoules.user.modules.published.PublishedAndroid;
import com.example.lilei.gank.modoules.user.modules.published.PublishedFrontend;
import com.example.lilei.gank.modoules.user.modules.published.PublishedIos;
import com.example.lilei.gank.modoules.user.modules.published.PublishedOther;
import com.example.lilei.gank.modoules.user.modules.setting.SettingActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by lilei on 2017/9/10.
 */

public class UserFragment extends BaseFragment implements IUserView, View.OnClickListener {

    private UserPresenter mUserPresenter;

    private ImageView ivBackground;
    private CircleImageView ciProfile;
    private TextView tvName;
    private TextView tvTag;
    //    private TextView tvModifyInfo;
    private TextView tvPublishedAndroid;
    private TextView tvPublishedIos;
    private TextView tvPublishedFrontend;
    private TextView tvPublishedOther;
    private ImageButton ibSetting;
    private TextView tvMessage;
    private TextView tvCollection;
    private TextView tvSetting;
    private TextView tvRight;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mUserFragment = inflater.inflate(R.layout.fragment_user, null);
        initView(mUserFragment);
        mUserPresenter.initPage();
        return mUserFragment;
    }

    private void initView(View view) {
        mUserPresenter = new UserPresenter();

        ivBackground = (ImageView) view.findViewById(R.id.user_background);
        ciProfile = (CircleImageView) view.findViewById(R.id.user_profile);
        tvName = (TextView) view.findViewById(R.id.user_name);
        tvTag = (TextView) view.findViewById(R.id.user_tag);
//        tvModifyInfo = (TextView) view.findViewById(R.id.user_modifyInfo);
        tvPublishedAndroid = (TextView) view.findViewById(R.id.user_published_android);
        tvPublishedIos = (TextView) view.findViewById(R.id.user_published_ios);
        tvPublishedFrontend = (TextView) view.findViewById(R.id.user_published_frontend);
        tvPublishedOther = (TextView) view.findViewById(R.id.user_published_other);
        ibSetting = (ImageButton) view.findViewById(R.id.user_settingImageButton);
        tvMessage = (TextView) view.findViewById(R.id.user_message);
        tvCollection = (TextView) view.findViewById(R.id.user_collection);
        tvSetting = (TextView) view.findViewById(R.id.user_settingTextView);
        tvRight = (TextView) view.findViewById(R.id.use_right);

        ciProfile.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvTag.setOnClickListener(this);
//        tvModifyInfo.setOnClickListener(this);
        tvPublishedAndroid.setOnClickListener(this);
        tvPublishedIos.setOnClickListener(this);
        tvPublishedFrontend.setOnClickListener(this);
        tvPublishedOther.setOnClickListener(this);
        ibSetting.setOnClickListener(this);
        tvMessage.setOnClickListener(this);
        tvCollection.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvRight.setOnClickListener(this);

        if (AVUser.getCurrentUser() != null){
            tvName.setText(AVUser.getCurrentUser().getUsername());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_profile:
                if (AVUser.getCurrentUser() == null){
                    startIntentActivity(this, new LoginActivity());
                }
                break;
            case R.id.user_name:
                if (AVUser.getCurrentUser() == null){
                    startIntentActivity(this, new LoginActivity());
                }
                break;
            case R.id.user_settingImageButton:
                startIntentActivity(this, new SettingActivity());
                break;
            case R.id.user_published_android:
                startIntentActivity(this, new PublishedAndroid());
                break;
            case R.id.user_published_ios:
                startIntentActivity(this, new PublishedIos());
                break;
            case R.id.user_published_frontend:
                startIntentActivity(this, new PublishedFrontend());
                break;
            case R.id.user_published_other:
                startIntentActivity(this, new PublishedOther());
                break;
            case R.id.user_message:
                startIntentActivity(this, new MessageActivity());
                break;
            case R.id.user_collection:
                startIntentActivity(this, new CollectionActivity());
                break;
            case R.id.user_settingTextView:
                startIntentActivity(this, new SettingActivity());
                break;
            case R.id.use_right:
                startIntentActivity(this, new RightActivity());
                break;
        }
    }

    @Override
    public void changeNameStatus(int changeTo) {
        switch (changeTo){
            case C.LOGEDIN:
                tvName.setText(AVUser.getCurrentUser().getUsername());
                break;
            case C.UNLOGEDIN:
                tvName.setText(R.string.login);
                break;
        }
    }
}
