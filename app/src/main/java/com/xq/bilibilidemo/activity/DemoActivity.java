package com.xq.bilibilidemo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.adapter.DemoActivityListAdapter;
import com.xq.bilibilidemo.ui.myself.RecyclerViewAdapter;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_demo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getWindow().getContext());
        recyclerView.setLayoutManager(layoutManager);

        DemoActivityListAdapter demoActivityListAdapter = new DemoActivityListAdapter(getWindow().getContext());
        recyclerView.setAdapter(demoActivityListAdapter);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色

    }
}


