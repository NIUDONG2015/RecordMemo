package com.example.tailin.privatepage.common.util;

import android.util.Log;

/**
 * Log打印类
 */
public class LogUtil {

    //应用上线将其置为false,在application中改变
    public final static boolean DEBUG = true;

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, Object msg) {
        if (DEBUG) {
            Log.i(tag, msg.toString());
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, Object msg) {
        if (DEBUG) {
            Log.w(tag, msg.toString());
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, Object msg) {
        if (DEBUG) {
            Log.e(tag, msg.toString());
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, Object msg) {
        if (DEBUG) {
            Log.d(tag, msg.toString());
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, Object msg) {
        if (DEBUG) {
            Log.v(tag, msg.toString());
        }
    }

}
