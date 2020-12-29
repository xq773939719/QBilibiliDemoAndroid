package com.xq.bilibilidemo.ui.channel;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TabViewModel extends ViewModel {

    private ArrayList<String> tabNameArray;

    public TabViewModel() {
        tabNameArray = new ArrayList<String>();
        tabNameArray.add("频道");
        tabNameArray.add("分区");
    }

    public String getData(int index) {
        return this.tabNameArray.get(index);
    }

    public int getSize() {
        return tabNameArray.size();
    }
}