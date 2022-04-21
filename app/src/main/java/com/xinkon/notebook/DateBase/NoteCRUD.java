package com.xinkon.notebook.DateBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xinkon.notebook.entity.Note;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xinkonman
 * @desription:
 * @date: 2022/4/20 16:52
 */

public class NoteCRUD {
    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;

    //方便获取数据时调用
    private static final String[] columns = {
            NoteDateBase.ID,
            NoteDateBase.TITLE,
            NoteDateBase.CONTENT,
            NoteDateBase.TIME,
            NoteDateBase.MODE,
            NoteDateBase.COLLECT
    };


    //初始化数据库
    public NoteCRUD(Context context) {
        dbHelper = new NoteDateBase(context);
    }

    //开启数据库
    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    //关闭数据库
    public void close() {
        dbHelper.close();
    }

    //添加笔记
    public Note addNote(Note note) {
        //使用ContentValues 来对要添加的数据进行组装
        ContentValues values = new ContentValues();
        values.put(NoteDateBase.TITLE, note.getTitle());
        values.put(NoteDateBase.CONTENT, note.getContent());
        values.put(NoteDateBase.TIME, note.getTime());
        values.put(NoteDateBase.COLLECT, note.getCollect());
        values.put(NoteDateBase.MODE, note.getTag());
        //插入一条数据会返回插入数据的id,因为id会自增长
        long insertId = db.insert(NoteDateBase.TBALE_NAME, null, values);
        note.setId(insertId);
        return note;
    }

    //根据id获取对应单一笔记
    public Note getNode(long id) {
        Cursor cursor = db.query(NoteDateBase.TBALE_NAME, columns, NoteDateBase.ID + "= ?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //不需要id，因为是查询到用于去展示标题和内容
        Note e = new Note(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getInt(4), cursor.getInt(5));
        return e;
    }

    //获取所有笔记
    public List<Note> getAllNotes() {
        Cursor cursor = db.query(NoteDateBase.TBALE_NAME, null, null, null, null, null, null);

        List<Note> notes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                //cursor.getColumnIndex()获取到下标
                note.setId(cursor.getLong(cursor.getColumnIndex("id")));
                note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                note.setContent(cursor.getString(cursor.getColumnIndex("content")));
                note.setTime(cursor.getString(cursor.getColumnIndex("time")));
                note.setTag(cursor.getInt(cursor.getColumnIndex("mode")));
                note.setCollect(cursor.getInt(cursor.getColumnIndex("collect")));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        return notes;
    }

    //更新笔记
    public int updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteDateBase.TITLE, note.getTitle());
        values.put(NoteDateBase.CONTENT, note.getContent());
        values.put(NoteDateBase.TIME, note.getTime());
        values.put(NoteDateBase.MODE, note.getTag());
        values.put(NoteDateBase.COLLECT, note.getCollect());
        return db.update(NoteDateBase.TBALE_NAME, values, NoteDateBase.ID + "= ? ",
                new String[]{String.valueOf(note.getId())});
    }

    //删除笔记
    public void deleteNote(long id) {
        db.delete(NoteDateBase.TBALE_NAME, NoteDateBase.ID + "= ?", new String[]{String.valueOf(id)});
    }

}
