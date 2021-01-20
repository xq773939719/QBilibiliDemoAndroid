package com.xq.bilibilidemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.xq.bilibilidemo.R;

import java.io.File;

public class DemoActivityOfMedia extends AppCompatActivity {

    private static final String TAG = DemoActivityOfMedia.class.toString();
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
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
            int currentTime = Math
                    .round(mediaPlayer.getCurrentPosition() / 1000);
            String currentStr = String.format("%s%02d:%02d", "当前时间 ",
                    currentTime / 60, currentTime % 60);
            TextView tv = findViewById(R.id.text_progress);
            tv.setText(currentStr);
        }

    };

    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.test);
    }

    private void initVideoView() {
        videoView = findViewById(R.id.video_view);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            videoView.setVideoURI(Uri.parse("android.resource://com.xq.bilibilidemo/" + R.raw.go));
            Log.d(TAG, "initVideoView: ");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_media);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

        /*1.create from raw*/
        initMediaPlayer();
        Button buttonStartMusic = findViewById(R.id.button_start_music);
        buttonStartMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                int totalTime = Math.round(mediaPlayer.getDuration() / 1000);
                String str = String.format("%02d:%02d", totalTime / 60, totalTime % 60);
                TextView textTotal = findViewById(R.id.text_total);
                textTotal.setText(str);
                mHandler.postDelayed(runnable, 1000);
            }
        });
        Button buttonPauseMusic = findViewById(R.id.button_pause_music);
        buttonPauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
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

        initVideoView();

        Button buttonStartVideo = findViewById(R.id.button_start_video);
        buttonStartVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
            }
        });
        Button buttonPauseVideo = findViewById(R.id.button_pause_video);
        buttonPauseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.pause();
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

        /*String prefix = "http://music.163.com/song/media/outer/url?id=";
        String id = "22815040.mp3";
        String url = prefix + id;
        Log.d(TAG, "onCreate: " + url);*/
        /*MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.start();
                    Log.d(TAG, "run: ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).run();*/
    }
}