package com.xq.bilibilidemo.common;

import java.util.ArrayList;
import java.util.Random;

public class Common {

    public static String getRandomColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }

    public static ArrayList<String> getRandomColorArray(int sum) {
        ArrayList<String> data = new ArrayList<String>();
        for(int i = 0; i< sum; ++i) {
            data.add(getRandomColor());
        }
        return data;
    }

    public static Integer getRandomData(int num) {
        Random random = new Random();//默认构造方法
        return random.nextInt(num);
    }

    public static Integer getRandomData(int start, int end) {
        Random random = new Random();
        return random.nextInt(end)%(end-start+1) + start;
    }

    public static ArrayList<Integer> getRandomDataArray(int sum) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        Random random = new Random();//默认构造方法
        for(int i = 0; i < sum; ++i) {
            arrayList.add(random.nextInt(sum));
        }
        return arrayList;
    }

}
