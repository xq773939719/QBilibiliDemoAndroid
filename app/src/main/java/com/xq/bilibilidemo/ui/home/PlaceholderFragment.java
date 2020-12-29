package com.xq.bilibilidemo.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.google.android.material.snackbar.Snackbar;
import com.xq.bilibilidemo.R;
import com.xq.bilibilidemo.common.Common;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    public int index;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.index = index;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pager, container, false);
        root.setBackgroundColor(Color.parseColor(Common.getRandomColor()));

        SwipeRefreshLayout refreshLayout = root.findViewById(R.id.per_pager);
        refreshLayout.setColorSchemeResources(R.color.Primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        root.setBackgroundColor(Color.parseColor(Common.getRandomColor()));
                        refreshLayout.setRefreshing(false);
                        Snackbar.make(getView(), "更新完毕", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }, 1000);

            }
        });
        return root;
    }
}