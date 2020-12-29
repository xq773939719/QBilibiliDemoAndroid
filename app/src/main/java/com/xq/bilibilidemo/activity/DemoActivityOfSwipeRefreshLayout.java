package com.xq.bilibilidemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Common;

public class DemoActivityOfSwipeRefreshLayout extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_swiper);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

        SwipeRefreshLayout refreshLayout = findViewById(R.id.swipe_refresh_Layout);
        refreshLayout.setColorSchemeResources(R.color.Primary);
        refreshLayout.setProgressViewOffset(true, 50, 200); // //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        refreshLayout.setSize(SwipeRefreshLayout.LARGE); //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light); // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        refreshLayout.setEnabled(true); // 通过 setEnabled(false) 禁用下拉刷新
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);

            }
        });


    }
}
