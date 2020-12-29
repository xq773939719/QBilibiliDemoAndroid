package com.xq.bilibilidemo.ui.news;

import androidx.lifecycle.ViewModel;

import com.xq.bilibilidemo.common.Common;

import java.util.ArrayList;

public class RecyclerViewViewModel extends ViewModel {
    private ArrayList<String> data;

    public RecyclerViewViewModel(int sum) {
        this.data = Common.getRandomColorArray(sum);
    }

    public void setData(ArrayList<String> data){
        this.data.clear();
        this.data.addAll(data);
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
