package com.xq.bilibilidemo.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TabViewModel extends ViewModel {

    private ArrayList<String> tabNameArray;

    public TabViewModel() {
        tabNameArray = new ArrayList<String>();
        tabNameArray.add("直播");
        tabNameArray.add("推荐");
        tabNameArray.add("热门");
        tabNameArray.add("追番");
        tabNameArray.add("影视");
        tabNameArray.add("抗击肺炎");
        tabNameArray.add("小康");
    }

    public String getData(int index) {
        return this.tabNameArray.get(index);
    }

    public int getSize() {
        return tabNameArray.size();
    }
}