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
            + ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + "TEXT,"
            + CONTENT + "TEXT NOT NULL,"
            + TIME + "TEXT NOT NULL,"
            + COLLECT + "INTEGER DEFAULT 0,"
            + MODE + "INTEGER DEFAULT 1)";

    public NoteDateBase(Context context) {
        super(context, "note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                //只能使用id,不能用字符串代替
                "CREATE TABLE note ("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                        + TITLE + " TEXT,"
                        + CONTENT + " TEXT NOT NULL,"
                        + TIME + " TEXT NOT NULL,"
                        + COLLECT + " INTEGER DEFAULT 0,"
                        + MODE + " INTEGER DEFAULT 1)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
