package com.xq.bilibilidemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.xq.bilibilidemo.R;

public class DemoActivityOfNavigationView extends AppCompatActivity {

    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_navi_view);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

        Toolbar toolbar = findViewById(R.id.nav_toolbar);
        toolbar.setTitle("标题"); // 设置标题
        toolbar.setSubtitle("副标题"); // 设置副标题
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); // 设置最左侧导航图标
        toolbar.setLogo(android.R.drawable.ic_menu_manage); // 设置当前页面logo
        toolbar.setElevation(4); // 设置仰角
        toolbar.setBackground(getResources().getDrawable(R.color.Primary)); // 设置背景颜色
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Snackbar.make(getWindow().getDecorView(), item.getTitle(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                //关闭Drawer
                drawerLayout.closeDrawers();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                0,
                0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);   //设置监听
        actionBarDrawerToggle.syncState(); //同步
    }
}
