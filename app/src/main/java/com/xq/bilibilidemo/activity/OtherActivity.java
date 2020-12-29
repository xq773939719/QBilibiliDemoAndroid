package com.xq.bilibilidemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.xq.bilibilidemo.R;

public class OtherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        TextView textView = findViewById(R.id.text_date);
        textView.setText(getIntent().getExtras().getString("value"));

        Snackbar.make(this.getWindow().getDecorView(), "通用的详情Activity页面", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

    }
}
