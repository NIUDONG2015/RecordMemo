package com.example.tailin.privatepage.base;

import android.app.Application;

/**
 * 所有的Application继承自此BaseActivity
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }

    protected abstract void initConfigs(); //初始化配置
}
