package com.xinkon.notebook.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author xinkonman
 * @desription:
 * @date: 2022/4/20 16:32
 */

public class NoteDateBase extends SQLiteOpenHelper {

    public static final String TBALE_NAME = "note";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String ID = "id";
    public static final String TIME = "time";
    public static final String COLLECT = "collect";
    //指的是tag标签
    public static final String MODE = "mode";


    public static final String CREATE_NOTE = "CREATE TABLE note("
            + ID +"integer primary key autoincrement,"
            + TITLE + "text,"
            + CONTENT + "text NOT NULL,"
            + TIME + "text NOT NULL,"
            + COLLECT + "integer DEFAULT 0,"
            + MODE + "integer DEFAULT 1)";

    public NoteDateBase(Context context) {
        super(context, "note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
