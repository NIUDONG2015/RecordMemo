package com.example.tailin.privatepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tailin.privatepage.demo.banner.BannerImageActivity;
import com.example.tailin.privatepage.demo.banner_countdown.SplashActivity;
import com.example.tailin.privatepage.demo.bottom_tab_toggle.TabActivity;
import com.example.tailin.privatepage.demo.flowlayout.FlowLayoutActivity;
import com.example.tailin.privatepage.demo.statusbar.StatusBarFullActivity;
import com.example.tailin.privatepage.demo.video.ListViewVideoActivity;
import com.example.tailin.privatepage.demo.video.SimpleVideoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void eventClcik(View view) {
        int id = view.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.btn_imge_scroll://图片无限轮播
                intent.setClass(MainActivity.this, BannerImageActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_flowlayout://流式布局
                intent.setClass(MainActivity.this, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_bottom_tab://隐藏或者显示底部tab
                intent.setClass(MainActivity.this, TabActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_video://视频播放
                intent.setClass(MainActivity.this, SimpleVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_video_list://列表
                intent.setClass(MainActivity.this, ListViewVideoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_count_down:
                intent.setClass(MainActivity.this, SplashActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_status_bar:
                intent.setClass(MainActivity.this, StatusBarFullActivity.class);
                startActivity(intent);
                break;

            default:
                break;

        }
    }
}
