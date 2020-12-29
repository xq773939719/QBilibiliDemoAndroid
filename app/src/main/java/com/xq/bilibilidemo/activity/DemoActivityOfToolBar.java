package com.xq.bilibilidemo.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xq.bilibilidemo.R;

public class DemoActivityOfToolBar extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_toolbar);

        String title = getIntent().getExtras().getString("target");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(title); // 设置标题
        toolbar.setSubtitle(title); // 设置副标题
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); // 设置最左侧导航图标
        toolbar.setLogo(android.R.drawable.ic_menu_manage); // 设置当前页面logo
        toolbar.setElevation(4); // 设置仰角
        toolbar.setBackground(getResources().getDrawable(R.color.Primary)); // 设置背景颜色
        setSupportActionBar(toolbar);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this,"Add button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this,"Remove button clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item1:
                Toast.makeText(this,"More button1 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item2:
                Toast.makeText(this,"More button2 clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.more_item3:
                Toast.makeText(this,"More button3 clicked",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
