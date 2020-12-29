package com.xq.bilibilidemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xq.bilibilidemo.activity.DemoActivity;
import com.xq.bilibilidemo.activity.OtherActivity;
import com.xq.bilibilidemo.R;

import java.util.Date;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_view, container, false);
        Fragment sub = new HomeSubFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.sub_fragment, sub).commit();

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OtherActivity.class);
                intent.putExtra("value", new Date().toString());
                startActivity(intent);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(getContext(), DemoActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return root;
    }
}