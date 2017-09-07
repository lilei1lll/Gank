package com.example.lilei.gank.component.util;

import android.util.Log;

/**
 * Created by lilei on 2017/9/7.
 */

public class PLog {

    public static final boolean PLOG_PRINT_LOG = true;

    /**
     * 错误信息
     */
    public static void e(String TAG, String msg) {
        if (PLOG_PRINT_LOG) {
            Log.e(TAG, log(msg));
        }

//        if (PLOG_WRITE_TO_FILE) {
//            writeLogtoFile("e", TAG, msg);
//        }
    }



    public static void e(String msg) {
        if (PLOG_PRINT_LOG) {
            Log.e("TAG", log(msg));
        }

//        if (PLOG_WRITE_TO_FILE) {
//            writeLogtoFile("e", TAG, msg);
//        }
    }

    /**
     * 警告信息
     */
    public static void w(String TAG, String msg) {
        if (PLOG_PRINT_LOG) {
            Log.w(TAG, log(msg));
        }

    }

    /**
     * 调试信息
     */
    public static void d(String TAG, String msg) {
        if (PLOG_PRINT_LOG) {
            Log.d(TAG, log(msg));
        }

    }

    /**
     * 提示信息
     */
    public static void i(String TAG, String msg) {
        if (PLOG_PRINT_LOG) {
            Log.i(TAG, log(msg));
        }

    }


    /**
     * 打印 Log 行数位置
     */
    public static String log(String message) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[5];
        String className = targetElement.getClassName();
        int i = className.indexOf("$");// 剔除匿名内部类名
        if (i != -1) {
            className = className.substring(0, i);
        }
        className = className.substring(className.lastIndexOf('.') + 1) + ".java";
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) lineNumber = 0;
        return "(" + className + ":" + lineNumber + ") " + message;
    }

}

