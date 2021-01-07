package com.xq.bilibilidemo.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xq.bilibilidemo.R;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;


public class DemoActivityOfLocalStorage extends AppCompatActivity {
    enum StorageType {
        LocalStorage, // 文件缓存，默认存储路径：/data/data/<PackageName>/files
        SharedPreferences, // 存储键值对，默认存储路径：/data/data/<PackageName>/shared_prefs
        SQLite, // 本地数据库，默认存储路径：/data/data/<PackageName>/databases
//        ContentProvider, // ContentProvider主要用于不同的程序之间实现数据共享的功能。
//        Network, // 后台存储
    }


    class SQLDataBaseHelper extends SQLiteOpenHelper {
        public static final String CREATE_BOOK = "create table book ( "
                + " id integer primary key autoincrement,"
                + " author text,"
                + "price real,"
                + "pages integer,"
                + "name text)";

        public SQLDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", "Book Name");
            values.put("author", "Q");
            values.put("pages", 1000);
            values.put("price", 10);
            sqLiteDatabase.insert("Book", null, values);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_BOOK);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    EditText editText = null;
    TextView textView = null;
    Button button = null;
    int index = 0;
    StorageType storageType = StorageType.LocalStorage;
    SQLDataBaseHelper sqlDataBaseHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//黑色
        setContentView(R.layout.activity_demo_local_storage);
        editText = findViewById(R.id.add_data_to_file);
        textView = findViewById(R.id.read_data_from_file);
        button = findViewById(R.id.read_data_button);
        sqlDataBaseHelper = new SQLDataBaseHelper(this, "test.db", null, 2);
        setEditText();
        setTextView();
        setButton();
    }

    private void setEditText() {
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    setWriter();
                    return true;
                }
                return false;
            }
        });
    }

    private void setTextView() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReader();
            }
        });
    }

    private void setButton() {
        Button button = findViewById(R.id.change_storage_type);
        button.setText("存储方式-" + storageType.toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageType[] values = StorageType.values();
                StorageType cur = values[index++ % values.length];
                storageType = cur;
                button.setText("存储方式-" + storageType.toString());
            }
        });
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "                android:layout_width=\"match_parent\"\n" +
                        "                android:layout_height=\"match_parent\">\n" +
                        "\n" +
                        "    <TextView\n" +
                        "                android:layout_width=\"match_parent\"\n" +
                        "                android:layout_height=\"match_parent\"\n" +
                        "                android:text=\"本地文件加载测试\"\n" +
                        "                android:gravity=\"center\"\n" +
                        "                android:textSize=\"36sp\" />\n" +
                        "</LinearLayout>";
                FileOutputStream out = null;
                BufferedWriter writer = null;
                try {
                    out = openFileOutput("XMLFile.xml", MODE_PRIVATE);
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    writer.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.flush();
                            writer.close();
                            Intent intent = new Intent(v.getContext(), DemoActivityOfXMLFileLayout.class);
                            startActivity(intent);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            }
        });
    }

    private void setWriter() {
        switch (storageType) {
            case LocalStorage:
                String data = editText.getText().toString();
                FileOutputStream out = null;
                BufferedWriter writer = null;
                try {
                    out = openFileOutput("data", Context.MODE_PRIVATE);
                    writer = new BufferedWriter(new OutputStreamWriter(out));
                    writer.write(data);
                    Log.d("XQXQ", "onKey: " + data);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) {
                            writer.flush();
                            writer.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SharedPreferences:
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("text", editText.getText().toString());
                editor.commit();
                break;
            case SQLite:
                SQLiteDatabase sqLiteDatabase = sqlDataBaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author", editText.getText().toString());
                sqLiteDatabase.update("Book", values, "name= ?", new String[]{"Book Name"});
                break;
//            case ContentProvider:
//                ContentResolver resolver = this.getContentResolver();
//                Uri uri = Uri.parse("content://com.xq.bilibilidemo");
//                values = new ContentValues();
//                values.put("author", editText.getText().toString());
//                resolver.update(uri, values, "name= ?", new String[]{"Book Name"});
//                break;
//            case Network:
//                // 可发送Post请求修改后台数据
//                break;
        }

    }

    private void setReader() {
        switch (storageType) {
            case LocalStorage:
                FileInputStream in = null;
                BufferedReader reader = null;
                StringBuilder builder = new StringBuilder();
                try {
                    in = openFileInput("data");
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    Log.d("XQXQ", "onClick: " + builder.toString());
                    textView.setText(builder.toString());
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
                break;
            case SharedPreferences:
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                String s = sharedPreferences.getString("text", null);
                textView.setText(s);
                break;
            case SQLite:
                SQLiteDatabase sqLiteDatabase = sqlDataBaseHelper.getWritableDatabase();
                Cursor cursor = sqLiteDatabase.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("author"));
                        textView.setText(name);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                break;
//            case ContentProvider:
//                ContentResolver resolver = this.getContentResolver();
//                Uri uri = Uri.parse("content://com.xq.bilibilidemo");
//                Cursor c = resolver.query(uri, null, null, null, null);
//                if (c.moveToFirst()) {
//                    for (int i = 0; i < c.getCount(); i++) {
//                        int index = c.getColumnIndexOrThrow("author");
//                        String src = c.getString(index);
//                        textView.setText(src);
//                        c.moveToNext();
//                    }
//                }
//                break;
//            case Network:
//                // 可发送get请求获取后台数据
//                break;
        }

    }
}
