package com.example.lilei.gank.component.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by skylineTan on 2016/3/8.
 * update by hugo
 */
public class CommonUtil {

    //版本号
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //版本号的名称
    public static String getVersionName(Context context) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(context.getPackageName(), 0);
            return _info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    //是否联网
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * Java判断字符是否是汉字，界限【 19968--171941 】 只通过中文
     * Hugo
     */
    public static boolean isRightUserName(String userName) {
        char[] chars = userName.toCharArray();
        if (chars.length <= 0) {
            return false;
        }
        for (char c : chars) {
            if (Character.isLetter(c) && c < 19968) {
                return false;
            }
        }
        return true;
    }

    /**
     * 模仿知乎在列表页不展示图片,用 [图片] 展示
     *
     * @param html 返回
     *             <p>
     *             Hugo
     */
    public static String replaceImgHtml(String html) {
        String pattern = "<img .*?>";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(html);
        return m.replaceAll("[图片]");
    }

    public static String replaceImgHtmlOnce(String html) {
        String pattern = "<img .*?>";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(replaceBrHtml(html));
        m = p.matcher(m.replaceFirst("[图片]"));
        return m.replaceAll("");
    }

    public static String replaceBrHtml(String html) {
        String pattern = "<br>";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }


    /**
     * 解决一些后台返回的空字段导致空指针.
     * <p>
     * Hugo
     */
    public static String safeText(String msg) {
        if (TextUtils.isEmpty(msg)) return "";
        return msg;
    }

    public static String safeText(int msg) {
        return safeText(msg + "");
    }

    public static String safeText(float msg) {
        return safeText(msg + "");
    }

    public static String safeAddText(String msg){
        if (TextUtils.isEmpty(msg)) return "";
        return " " + msg + " ";
    }
    /**
     * 控制小数点后两位
     *
     * @param editText
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    } else if (s.length() - 1 - s.toString().indexOf(".") == 2 && s.toString().endsWith("0")) {
                        editText.setText(s.subSequence(0, s.length() - 1));
                        editText.setSelection(s.length() - 1);
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }



}
