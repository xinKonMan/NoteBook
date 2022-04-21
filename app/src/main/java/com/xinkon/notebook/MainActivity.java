package com.xinkon.notebook;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xinkon.notebook.DateBase.NoteCRUD;
import com.xinkon.notebook.DateBase.NoteDateBase;
import com.xinkon.notebook.activity.BaseActivity;
import com.xinkon.notebook.activity.EditActivity;
import com.xinkon.notebook.adapter.EditAdapter;
import com.xinkon.notebook.entity.Note;
import com.xinkon.notebook.fragment.NoteFragment;
import com.xinkon.notebook.fragment.TargetFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {



    private NoteFragment noteFragment;
    private TargetFragment targetFragment;
    private Button btn_note;
    private Button btn_target;

    private NoteCRUD db;
    private List<Note> notes = new ArrayList<>();
    private EditAdapter editAdapter;


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btn_note = findViewById(R.id.note);
        btn_target = findViewById(R.id.target);
        noteFragment = new NoteFragment();
        targetFragment = new TargetFragment();

        db = new NoteCRUD(getApplicationContext());

    }

    @Override
    protected void initData() {

        //先加载初始fragment
        getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.frg_edit, noteFragment, "note").commitAllowingStateLoss();

        //跳转到noteFragment
        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frg_edit, noteFragment, "note").commitAllowingStateLoss();
            }
        });

        //跳转到targetFragment
        btn_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frg_edit, targetFragment, null).commitAllowingStateLoss();
            }
        });
        //创建适配器
        editAdapter = new EditAdapter(notes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");
        String time = data.getStringExtra("time");
        Note note = new Note(title, content, time, 1, 0);
        db.open();
        db.addNote(note);
        db.close();
        noteFragment.refreshRecycleView();
        Log.d("nice","是否执行");
    }

    //刷新数据
    public void refreshRecycleView() {
        //调用数据库
        NoteCRUD db = new NoteCRUD(getApplicationContext());
        db.open();
        //有数据先清除
        if (notes.size() > 0) {
            notes.clear();
        }
        //重新添加数据
        notes.addAll(db.getAllNotes());
        db.close();
        editAdapter.notifyDataSetChanged();
    }

}