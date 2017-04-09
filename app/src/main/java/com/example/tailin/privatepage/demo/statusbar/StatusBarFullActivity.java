package com.example.tailin.privatepage.demo.statusbar;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.example.tailin.privatepage.R;
import com.example.tailin.privatepage.base.BaseActivity;

/**
 * Created by niudng on 2017/4/9.
 * 参考地址：http://blog.csdn.net/guolin_blog/article/details/51763825
 */


public class StatusBarFullActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_status_bar;
    }

    @Override
    protected void initView() {
        hideStatus();

//      hideNavigation();
    }

    /**
     * 隐藏底部导航栏和顶部状态栏
     */
    private void hideNavigation() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /**
     * 效果：顶部隐藏状态栏   显示时间、网络标识、这时候底部有导航栏
     * 注意两个Flag必须要结合在一起使用，表示会让应用的主体内容占用系统状态栏的空间
     */
    private void hideStatus() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /**
     * 真正的沉浸式状态栏
     * 需要重写Activity的onWindowFocusChanged()方法
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
  /*      if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }*/
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void processClick(View view) {

    }
}
