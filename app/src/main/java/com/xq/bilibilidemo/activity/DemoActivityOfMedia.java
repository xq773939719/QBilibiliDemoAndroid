package com.xq.bilibilidemo.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.SelectMediaUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DemoActivityOfMedia extends AppCompatActivity {

    private static final String TAG = DemoActivityOfMedia.class.toString();
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    private ImageView imageView;
    private SelectMediaUtil selectMediaUtil;
    private Activity self;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0x01:
                    break;
                default:
                    break;
            }
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(this, 1000);
            int currentTime = Math.round(mediaPlayer.getCurrentPosition() / 1000);
            String currentStr = String.format("%s%02d:%02d", "当前时间 ", currentTime / 60, currentTime % 60);
            TextView tv = findViewById(R.id.text_progress);
            tv.setText(currentStr);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_media);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        initMediaPlayer();
        initVideoView();
        initImageView();
        selectMediaUtil = new SelectMediaUtil();
        self = this;
    }

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.test);
        Button buttonStartOrPauseMusic = findViewById(R.id.button_start_or_pause_music);
        buttonStartOrPauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    int totalTime = Math.round(mediaPlayer.getDuration() / 1000);
                    String str = String.format("%02d:%02d", totalTime / 60, totalTime % 60);
                    TextView textTotal = findViewById(R.id.text_total);
                    textTotal.setText(str);
                    mHandler.postDelayed(runnable, 1000);
                    buttonStartOrPauseMusic.setText("暂停");
                } else {
                    mediaPlayer.pause();
                    buttonStartOrPauseMusic.setText("播放");
                }
            }
        });
        Button buttonStopMusic = findViewById(R.id.button_stop_music);
        buttonStopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                initMediaPlayer();
            }
        });
        Button buttonSelectMusic = findViewById(R.id.button_select_music);
        buttonSelectMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMediaUtil.select(self, SelectMediaUtil.SelectType.audio);
            }
        });
    }

    private void initVideoView() {
        videoView = findViewById(R.id.video_view);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            videoView.setVideoURI(Uri.parse("android.resource://com.xq.bilibilidemo/" + R.raw.go));
            Log.d(TAG, "initVideoView: ");
        }

        Button buttonStartOrPauseVideo = findViewById(R.id.button_start_or_pause_video);
        buttonStartOrPauseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoView.isPlaying()) {
                    videoView.start();
                    buttonStartOrPauseVideo.setText("暂停");
                } else {
                    videoView.pause();
                    buttonStartOrPauseVideo.setText("播放");
                }
            }
        });

        Button buttonStopVideo = findViewById(R.id.button_stop_video);
        buttonStopVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.stopPlayback();
                initVideoView();
            }
        });

        Button buttonSelectVideo = findViewById(R.id.button_select_video);
        buttonSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMediaUtil.select(self, SelectMediaUtil.SelectType.video);
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void initImageView() {
        imageView = findViewById(R.id.image_view);
        imageView.setBackgroundColor(R.color.design_default_color_secondary_variant);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectMediaUtil.select(self, SelectMediaUtil.SelectType.image);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = selectMediaUtil.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;
        Log.d(TAG, "onActivityResult: " + path);
        Log.d(TAG, "onActivityResult: " + requestCode);
        switch (requestCode) {
            case 1: {
                setMedia(path);
                break;
            }
            case 2: {
                setVideo(path);
                break;
            }
            case 3: {
                showImage(path);
                break;
            }
        }


    }

    private void setMedia(String path) {
        mediaPlayer.stop();
        mediaPlayer = null;
        Uri uri = Uri.fromFile(new File(path));
        mediaPlayer = MediaPlayer.create(getBaseContext(), uri);
        Button buttonStartOrPauseMusic = findViewById(R.id.button_start_or_pause_music);
        buttonStartOrPauseMusic.setText("播放");
    }

    private void setVideo(String path) {
        videoView.stopPlayback();
        videoView.setVideoPath(path);
        Button buttonStartOrPauseVideo = findViewById(R.id.button_start_or_pause_video);
        buttonStartOrPauseVideo.setText("播放");
    }

    //加载图片
    private void showImage(String imagePath) {
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        imageView.setImageBitmap(bm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        if (videoView != null) {
            videoView.stopPlayback();
            videoView.suspend();
        }
    }
}