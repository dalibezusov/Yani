package com.yani.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final String CONTENT_AUTHORITY = "com.yani.musicians";

    private static final String SCHEME = "content://";

    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + CONTENT_AUTHORITY);

    private static final String DATABASE_NAME = "com.yani.database.musician_db";

    private static final int DATABASE_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MusiciansTable.Requests.CREATION_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MusiciansTable.Requests.DROP_REQUEST);
        onCreate(db);
    }


}
