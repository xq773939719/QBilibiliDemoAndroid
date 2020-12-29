package com.xq.bilibilidemo.ui.channel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.xq.bilibilidemo.R;

public class ChannelFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sub_view, container, false);
        root.findViewById(R.id.fab).setVisibility(View.INVISIBLE);

        Fragment sub = new ChannelSubFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.sub_fragment, sub).commit();

        return root;
    }
}
