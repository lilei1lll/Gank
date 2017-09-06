package com.example.lilei.gank.base;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by lilei on 2017/9/4.
 */

public class BaseActivity extends AppCompatActivity {

    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//状态栏
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintResource(color);
            tintManager.setStatusBarTintEnabled(true);
        }
    }



    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2){
        Intent intent = new Intent();
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }

    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2, String name, String value){
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }
    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2, String name, int value){
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }
    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2, String name1, int value1,String name2, int value2){
        Intent intent = new Intent();
        intent.putExtra(name1, value1);
        intent.putExtra(name2, value2);
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }
    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2, String name1, String value1,String name2, int value2){
        Intent intent = new Intent();
        intent.putExtra(name1, value1);
        intent.putExtra(name2, value2+"");
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }
    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2,
                                    String name1, String value1,String name2, int value2
            ,String name3,int value3){
        Intent intent = new Intent();
        intent.putExtra(name1, value1);
        intent.putExtra(name2, value2);
        intent.putExtra(name3, value3);
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }


    public void startIntentActivity(BaseActivity activity1,BaseActivity activity2,
                                    String name1, String value1,String name2, int value2
            ,String name3,String value3){
        Intent intent = new Intent();
        intent.putExtra(name1, value1);
        intent.putExtra(name2, ""+value2);
        intent.putExtra(name3, value3);
        intent.setClass(activity1, activity2.getClass());
        startActivity(intent);
    }

    public void startIntentActivity(BaseActivity activity1,Fragment fragment2){
        Intent intent = new Intent();
        intent.setClass(activity1, fragment2.getClass());
        startActivity(intent);
    }

    public void startIntentActivity(BaseActivity activity1,Fragment fragment2, String name, String value){
        Intent intent = new Intent();
        intent.putExtra(name, value);
        intent.setClass(activity1, fragment2.getClass());
        startActivity(intent);
    }

}
