package com.xq.bilibilidemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.activity.DemoActivityOfIJKPlayer;
import com.xq.bilibilidemo.activity.DemoActivityOfLearnAndroid;
import com.xq.bilibilidemo.activity.DemoActivityOfFloatingActionButton;
import com.xq.bilibilidemo.activity.DemoActivityOfMedia;
import com.xq.bilibilidemo.activity.DemoActivityOfNavigationView;
import com.xq.bilibilidemo.activity.DemoActivityOfSwipeRefreshLayout;
import com.xq.bilibilidemo.activity.DemoActivityOfToolBar;
import com.xq.bilibilidemo.common.Common;
import com.xq.bilibilidemo.model.DemoActivityViewModel;

public class DemoActivityListAdapter extends RecyclerView.Adapter<DemoActivityListAdapter.MyViewHolder> {

    private Context context;
    private DemoActivityViewModel model;

    public DemoActivityListAdapter(Context context) {
        this.context = context;
        this.model = new DemoActivityViewModel();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DemoActivityListAdapter.MyViewHolder myViewHolder = new DemoActivityListAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String str = this.model.getData(position);
        holder.textView.setBackgroundColor(Color.parseColor(Common.getRandomColor()));
        holder.textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360));
        holder.textView.setPadding(20, 20, 20, 20);
        holder.textView.setText(str);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getTargetActivity(str);
                intent.putExtra("value", "位置：" + position + "被点击，数值为" + str);
                intent.putExtra("target", str);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.getSize();
    }

    private Intent getTargetActivity(String targetStr) {
        switch (targetStr) {
            case "ToolBar":
                return new Intent(context, DemoActivityOfToolBar.class);
            case "NavigationView":
                return new Intent(context, DemoActivityOfNavigationView.class);
            case "FloatingActionButton":
                return new Intent(context, DemoActivityOfFloatingActionButton.class);
            case "SwipeRefreshLayout":
                return new Intent(context, DemoActivityOfSwipeRefreshLayout.class);
            case "LearnAndroid":
                return new Intent(context, DemoActivityOfLearnAndroid.class);
            case "Media":
                return new Intent(context, DemoActivityOfMedia.class);
            case "ijkPlayer":
                return new Intent(context, DemoActivityOfIJKPlayer.class);
        }
        return null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.list_item_view);
        }
    }

}
