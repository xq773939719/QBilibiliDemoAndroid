package com.xq.bilibilidemo.activity;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xq.bilibilidemo.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class DemoActivityOfXMLFileLayout extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_of_xml);
        ViewGroup root = findViewById(R.id.xml_layout);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色


        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            in = openFileInput("XMLFile.xml");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Log.d("XQXQ", "getLayoutXmlPullParser: " + builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        XmlPullParser xmlPullParser = getLayoutXmlPullParser();

        LayoutInflater inflater = getLayoutInflater();
        inflater.inflate(xmlPullParser, root, true);
    }

    private XmlPullParser getLayoutXmlPullParser() {

        XmlResourceParser parser = null;

        try {
            AssetManager asset = getResources().getAssets();
            Class<AssetManager> c = AssetManager.class;

            try {
                Method method = c.getMethod("addAssetPath", String.class);

                try {
                    int cookie = (Integer) method.invoke(asset, getFilesDir().getAbsolutePath());
                    parser = asset.openXmlResourceParser(cookie, "XMLFile.xml");
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }


        return parser;
    }
}
