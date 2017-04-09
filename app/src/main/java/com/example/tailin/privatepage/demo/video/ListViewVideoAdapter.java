package com.example.tailin.privatepage.demo.video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.tailin.privatepage.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tailin on 2017/3/31.
 * 支持全屏播放视频的列表适配器，不支持小窗口
 */
public class ListViewVideoAdapter extends BaseAdapter {

    public static final String TAG = "ListViewVideoAdapter";//必须设置
    private Context context;
    private LayoutInflater inflater = null;
    List<VideoModel> list;
    private boolean isFullVideo = false;

    public ListViewVideoAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        //初始化数据
        list = new ArrayList<>();
        for (int i = 0; i < VideoModel.VIDEO_NUM; i++) {
            list.add(new VideoModel(VideoModel.VIDEO_URL, VideoModel.VIDEO_TITLE));
        }
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_simple_video, null);
            holder = new ViewHolder(convertView, context);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重点来了，在这里
        //1.设置video封面
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageView.setImageResource(R.mipmap.ic_video_thumb);
        if (holder.imageView.getParent() != null) {
            ViewGroup vGroup = (ViewGroup) holder.imageView.getParent();
            vGroup.removeView(holder.imageView);
        }
        holder.gsyVideoPlayer.setThumbImageView(holder.imageView);
        //2.设置播放地址
        holder.gsyVideoPlayer.setUp(list.get(position).getUrl(), false, null, list.get(position).getTitle());
        //3.增加title
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //4.设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //5.设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resolveFullBtn(holder.gsyVideoPlayer);
            }
        });
        //设置当前播放的位置
        holder.gsyVideoPlayer.setRotateViewAuto(true);
        holder.gsyVideoPlayer.setLockLand(true);
        holder.gsyVideoPlayer.setPlayTag(TAG);
        holder.gsyVideoPlayer.setShowFullAnimation(true);
        holder.gsyVideoPlayer.setNeedLockFull(true);
        holder.gsyVideoPlayer.setPlayPosition(position);

        holder.gsyVideoPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                if (!holder.gsyVideoPlayer.isIfCurrentIsFullscreen()) {//如果没有进入全屏
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

        return convertView;
    }

    static class ViewHolder {
        StandardGSYVideoPlayer gsyVideoPlayer;
        ImageView imageView;

        public ViewHolder(View rootView, Context cxt) {
            gsyVideoPlayer = (StandardGSYVideoPlayer) rootView.findViewById(R.id.svp_video);
            imageView = new ImageView(cxt);
        }
    }

    /**
     * 全屏幕按键处理
     */
    private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
        standardGSYVideoPlayer.startWindowFullscreen(context, false, true);
        isFullVideo = true;
    }

}
