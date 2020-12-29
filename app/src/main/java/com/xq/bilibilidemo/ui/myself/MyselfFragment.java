package com.xq.bilibilidemo.ui.myself;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Common;

public class MyselfFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.recycler_view, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_common);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(recyclerViewAdapter);

        SwipeRefreshLayout refreshLayout = root.findViewById(R.id.recycler_view_swiper);
        refreshLayout.setColorSchemeResources(R.color.Primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerViewAdapter.clear();
                        recyclerViewAdapter.setData(Common.getRandomColorArray(Common.getRandomData(1000)));
                        refreshLayout.setRefreshing(false);
                        recyclerViewAdapter.notifyDataSetChanged();
                        Snackbar.make(getView(), "更新完毕", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }, 1000);
            }
        });

        return root;
    }
}
