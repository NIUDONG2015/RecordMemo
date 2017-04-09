package com.example.tailin.privatepage.demo.banner_countdown;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tailin.privatepage.MainActivity;
import com.example.tailin.privatepage.R;
import com.example.tailin.privatepage.common.util.ToastUtil;
import com.example.tailin.privatepage.widget.CountDownView;

/**
 * 广告页
 */
public class SplashActivity extends AppCompatActivity {

    //方法一
    private CountDownView countDownView;

    //方法二
    private boolean isOk = true;
    private int time = 10;
    private TextView tvTime;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                tvTime.setText("还剩" + time + "s");
                time--;
                if (time <= 0) {
                    isOk = false;
                }
            } else if (msg.what == 1) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initSplashView();
    }

    /**
     * 初始化
     */
    private void initSplashView() {
        ImageView ivSplash = (ImageView) findViewById(R.id.iv_splash);
        ivSplash.setImageResource(R.mipmap.ic_splash_one);
        countDownView = (CountDownView) findViewById(R.id.view_count_down);
        countDownView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {

            }

            @Override
            public void onFinishCount() {
                Toast.makeText(SplashActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (countDownView != null) {
            countDownView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownView.stop();
        countDownView = null;
    }

    private void fun2() {
        new Thread() {
            @Override
            public void run() {
                while (isOk) {
                    try {
                        Thread.sleep(1000);
                        if (time == 0) {
                            handler.sendEmptyMessage(1);
                        } else {
                            handler.sendEmptyMessage(0);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
