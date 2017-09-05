package com.example.lilei.gank.component.network;


import com.example.lilei.gank.C;

/**
 * Created by HugoXie on 16/5/20.
 *
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
public class ApiException extends RuntimeException {

    public ApiException(String detailMessage) {
        super(detailMessage);
    }

    public ApiException(int resultCode, String msg) {
        this(handleErrorCode(resultCode,msg));
    }

    private static String handleErrorCode(int code, String s) {
        String msg;
        switch (code) {
            case -1:
                msg = C.UNLOGIN;
                break;
            default:
                msg = s;
                break;
        }
        return msg;
    }

}
