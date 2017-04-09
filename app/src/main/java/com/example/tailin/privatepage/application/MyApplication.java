package com.example.tailin.privatepage.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;

import com.example.tailin.privatepage.base.BaseApplication;
import com.example.tailin.privatepage.common.util.LogUtil;

/**
 * application 用于应用级别的操作
 */
public class MyApplication extends BaseApplication {
    private static MyApplication instance;
    private static TelephonyManager sTelephonyManager;
    private static ActivityManager sActivityManager;
    public static Context context;

    public synchronized static MyApplication getInstance() {
        if (instance == null)
            instance = new MyApplication();
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                LogUtil.e("UncaughtException", ex.fillInStackTrace());
                // 程序奔溃，退出应用
                System.exit(0);
            }
        });
        initConfigs();
    }

    @Override
    protected void initConfigs() {
        context = getApplicationContext();
        sTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        sActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static TelephonyManager getTelephonyManager() {
        return sTelephonyManager;
    }

    public static ActivityManager getsActivityManager() {
        return sActivityManager;
    }
}
