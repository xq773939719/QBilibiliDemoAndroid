package com.xq.bilibilidemo.ui.vip;

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

import com.xq.bilibilidemo.activity.OtherActivity;
import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Common;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public MyViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.list_item_view);
        }
    }

    private Context context;
    private RecyclerViewViewModel model;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
        this.model = new RecyclerViewViewModel(Common.getRandomData(1000));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_view, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final String colorStr = this.model.getData(position);
        holder.textView.setBackgroundColor(Color.parseColor((colorStr)));
        holder.textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Common.getRandomData(200, 600)));
        holder.textView.setPadding(20, 20, 20,20);
        holder.textView.setText(colorStr);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OtherActivity.class);
                intent.putExtra("value", "位置：" + position + "被点击，数值为" + colorStr);
                context.startActivity(intent);
            }
        });
    }

    public void setData(ArrayList<String> data) {
        model.setData(data);
    }

    public void clear() {
        this.model.clear();
    }

    @Override
    public int getItemCount() {
        return this.model.getSize();
    }

    public String getItem(int position) {
        return this.model.getData(position);
    }
}
