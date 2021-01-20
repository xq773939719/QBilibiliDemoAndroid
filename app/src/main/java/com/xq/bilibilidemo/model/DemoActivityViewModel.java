package com.xq.bilibilidemo.model;

import androidx.lifecycle.ViewModel;

import com.xq.bilibilidemo.common.Common;

import java.util.ArrayList;

public class DemoActivityViewModel extends ViewModel {
    private ArrayList<String> data;

    public DemoActivityViewModel() {
        data = new ArrayList<String>();
        data = new ArrayList<String>();
        this.data.add("Media");
        this.data.add("ijkPlayer");
        this.data.add("ToolBar");
        this.data.add("NavigationView");
        this.data.add("FloatingActionButton");
        this.data.add("SwipeRefreshLayout");
        this.data.add("LearnAndroid");
    }

    public String getData(int index) {
        return this.data.get(index);
    }

    public int getSize() {
        return data.size();
    }

    public void clear() {
        this.data.clear();
    }
}
