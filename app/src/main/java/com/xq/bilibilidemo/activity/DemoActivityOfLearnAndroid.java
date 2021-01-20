package com.xq.bilibilidemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Keyboard;

public class DemoActivityOfLearnAndroid extends AppCompatActivity {

    private boolean keyboardIsShow = false;
    private Keyboard keyboard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_learn_android);

        keyboard = new Keyboard(getBaseContext());
        keyboard.setText("点我跳出键盘");
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton();
            }
        });

        LinearLayout linearLayout = findViewById(R.id.button_container);
        keyboard.setTextSize(20);
        keyboard.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayout.addView(keyboard);

//        final Button button = findViewById(R.id.button_to_keyboard);
//        button.setText("点我跳出键盘");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickButton();
//            }
//        });


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
    }

    private void clickButton() {
        keyboard.click();
    }
}