package com.example.tailin.privatepage.demo.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tailin.privatepage.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * 该{@link SimpleVideoActivity}类实现简单视频播放，使用{@link StandardGSYVideoPlayer} sdk中标准播放器，实现简单视频自动播放
 * 使用GitHub中的sdk，详情参考https://github.com/CarGuo/GSYVideoPlayer/
 * <p/>
 * 准备接入：
 * 布局文件：R.layout.activity_simple_video
 * 清单文件加入两个相关权限：ACCESS_NETWORK_STATE 和 INTERNET
 * 一张封面图片（不设置封面可以不用）：R.mipmap.ic_video_thumb
 */
public class SimpleVideoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_video);
        initGSYVideo();
    }

    /**
     * 初始化gsy播放器相关配置
     */
    private void initGSYVideo() {
        final StandardGSYVideoPlayer gsyVideoPlayer = (StandardGSYVideoPlayer) findViewById(R.id.svp_video);
        //video添加封面（一开始就自动播放的话，这个不起作用）
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_video_thumb);
        gsyVideoPlayer.setThumbImageView(imageView);

        //播放url（清单文件中记得加网络权限）
        String videoUrl = "http://baobab.wdjcdn.com/14564977406580.mp4";
        String videoTitle = "video title";

        //设置播放url（url，是否开始缓存，使用默认缓存路径，设置video title
        gsyVideoPlayer.setUp(videoUrl, false, null, videoTitle);

        //非全屏下，是否显示title
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //非全屏下，是否显示返回键(非全屏下返回键点击事件不起作用)
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //非全屏下，是否可以滑动界面改变进度，声音，默认不可
        gsyVideoPlayer.setIsTouchWiget(true);
        //是否需要显示流量提示,默认true
        gsyVideoPlayer.setNeedShowWifiTip(true);
        //循环播放
        gsyVideoPlayer.setLooping(true);
        //开启自动旋转
        gsyVideoPlayer.setRotateViewAuto(true);
        gsyVideoPlayer.setLockLand(true);
        gsyVideoPlayer.setShowFullAnimation(true); //是否显示全屏动画
        gsyVideoPlayer.setNeedLockFull(true);//是否锁定屏幕
        //全屏播放
        gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gsyVideoPlayer.startWindowFullscreen(SimpleVideoActivity.this, false, true);
            }
        });
        //设置屏幕旋转时静音模式
        gsyVideoPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                if (!gsyVideoPlayer.isIfCurrentIsFullscreen()) {//如果没有进入全屏
                    GSYVideoManager.instance().setNeedMute(true);//静音
                }
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                GSYVideoManager.instance().setNeedMute(true);
            }

            @Override
            public void onEnterFullscreen(String url, Object... objects) {
                super.onEnterFullscreen(url, objects);
                GSYVideoManager.instance().setNeedMute(false);
            }
        });
        //点击封面可以播放 （清单文件中一定要加入 ACCESS_NETWORK_STATE 权限，代码中逻辑会去判断是否处于wifi状态）
        gsyVideoPlayer.setThumbPlay(true);
        //自动立即播放（清单文件中一定要加入 INTERNET 权限）
//        gsyVideoPlayer.startPlayLogic();
    }

    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
    }
}
