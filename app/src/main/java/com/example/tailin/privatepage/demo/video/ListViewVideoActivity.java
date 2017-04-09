package com.example.tailin.privatepage.demo.video;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.tailin.privatepage.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * listview列表视频实现
 * {@link ListViewVideoAdapter,SampleListener}
 * 准备接入
 * 1.布局文件 R.layout.activity_list_view_video
 * 2.ListViewVideoAdapter:item布局 R.layout.activity_simple_video
 * 3.SampleListener：直接复制
 * 4.style中的透明主题
 * 记得在清单文件中配置
 * <activity
 * android:name=".demo.video.ListViewVideoActivity"
 * android:configChanges="orientation|keyboardHidden|screenSize"
 * android:screenOrientation="portrait"
 * android:theme="@style/Theme.AppCompat.Translucent"></activity>
 */
public class ListViewVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //设置一个exit transition
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_video);
        initView();
    }

    private void initView() {
        ListView videoList = (ListView) findViewById(R.id.video_list);//listview列表
        RelativeLayout rlRootLayout = (RelativeLayout) findViewById(R.id.activity_list_video);//listview列表
        //设置适配器
        final ListViewVideoAdapter adapter = new ListViewVideoAdapter(this);
        videoList.setAdapter(adapter);
        //lisview设置监听
        videoList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(ListViewVideoAdapter.TAG)
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoPlayer.releaseAllVideos();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


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
