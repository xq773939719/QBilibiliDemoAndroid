package com.xq.bilibilidemo.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Keyboard;

public class DemoActivityLearnAndroid extends AppCompatActivity {

    private boolean keyboardIsShow = false;
    private Keyboard keyboard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_learn_android);

        keyboard = new Keyboard(getWindow().getDecorView());
        final FloatingActionButton fab = findViewById(R.id.fab_to_keyboard);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton(fab);
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
    }

    private void clickButton(FloatingActionButton fab) {
        if (!keyboardIsShow) {
            keyboard.show();
            keyboardIsShow = true;
        }
        else {
            keyboard.hide();
            keyboardIsShow = false;
        }
    }
}