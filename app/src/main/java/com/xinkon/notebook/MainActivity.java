package com.xinkon.notebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
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

    private Toolbar toolbar;


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

        toolbar = findViewById(R.id.note_toolbar);

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

        setSupportActionBar(toolbar);
        // setDisplayHomeAsUpEnabled() 方法让导航按钮显示出来
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int resultMode = data.getIntExtra("resultMode", -1);
        //获取id值用于保存更新数据,没有获取到id值时则默认为0
        long node_id = data.getLongExtra("id", 0);

        if (resultMode == 1) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String time = data.getStringExtra("time");
            int tag = data.getIntExtra("tag", 1);
            int collect = data.getIntExtra("collect", 0);
            Note note = new Note(node_id, title, content, time, tag, collect);
            db.open();
            db.updateNote(note);
            db.close();

        } else if (resultMode == 0) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");
            String time = data.getStringExtra("time");
            int tag = data.getIntExtra("tag", 1);
            int collect = data.getIntExtra("collect", 0);
            Note note = new Note(title, content, time, tag, collect);
            db.open();
            db.addNote(note);
            db.close();
        } else {

        }
        noteFragment.refreshRecycleView();

    }


}