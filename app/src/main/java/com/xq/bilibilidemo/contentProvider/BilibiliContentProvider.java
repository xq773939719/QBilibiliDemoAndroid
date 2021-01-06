package com.xq.bilibilidemo.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BilibiliContentProvider extends ContentProvider {

    public static final String CREATE_BOOK = "create table book ( "
            + " id integer primary key autoincrement,"
            + " author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";

    @Override
    public boolean onCreate() {
        SQLiteDatabase db = this.getContext().openOrCreateDatabase("test_content_provider.db3", Context.MODE_PRIVATE, null);
        db.execSQL(CREATE_BOOK);
        ContentValues values = new ContentValues();
        values.put("name", "Book Name");
        values.put("author", "Q_content_provider");
        values.put("pages", 1000);
        values.put("price", 10);
        db.insert("Book", null, values);
        db.close();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = this.getContext().openOrCreateDatabase("test_content_provider.db3", Context.MODE_PRIVATE, null);

        Cursor c = db.query("Book", null, null, null, null, null,null);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = this.getContext().openOrCreateDatabase("test_content_provider.db3", Context.MODE_PRIVATE, null);

        return db.update("Book", values, selection, selectionArgs);
    }
}
