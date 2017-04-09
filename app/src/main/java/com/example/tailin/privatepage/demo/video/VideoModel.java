package com.example.tailin.privatepage.demo.video;

/**
 * Created by tailin on 2017/3/31.
 * 视频model
 */
public class VideoModel {
    public static final int VIDEO_NUM = 20;
    public static String VIDEO_URL = "http://baobab.wdjcdn.com/14564977406580.mp4";
    public static String VIDEO_TITLE = "视频标题";
    String url;//视频播放地址
    String title;

    public VideoModel(String url, String tiltle) {
        this.url = url;
        this.title = tiltle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
