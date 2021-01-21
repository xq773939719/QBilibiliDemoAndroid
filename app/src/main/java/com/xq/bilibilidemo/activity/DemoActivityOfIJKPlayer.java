package com.xq.bilibilidemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.view.CustomPlayerWithIJKPlayer;

import java.io.IOException;
import java.util.ArrayList;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class DemoActivityOfIJKPlayer extends AppCompatActivity implements CustomPlayerWithIJKPlayer.VideoListener {

    private CustomPlayerWithIJKPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_i_j_k_player);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

        videoPlayer = findViewById(R.id.custom_ijkplayer);

        videoPlayer.setVideoListener(this);

        videoPlayer.setPath("http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4");
        try {
            videoPlayer.load();
        } catch (IOException e) {
            Toast.makeText(this, "播放失败", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }
        videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoPlayer.isPlaying()) videoPlayer.pause();
                else videoPlayer.start();
            }
        });

    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {

    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        videoPlayer.start();
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.stop();
        videoPlayer.release();
    }
}