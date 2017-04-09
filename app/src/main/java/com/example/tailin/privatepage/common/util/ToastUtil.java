package com.example.tailin.privatepage.common.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具类
 */
public class ToastUtil {

    public static void showShortMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
