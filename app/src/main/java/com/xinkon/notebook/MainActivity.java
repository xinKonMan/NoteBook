package com.xinkon.notebook;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xinkon.notebook.activity.BaseActivity;
import com.xinkon.notebook.activity.EditActivity;
import com.xinkon.notebook.fragment.NoteFragment;
import com.xinkon.notebook.fragment.TargetFragment;

public class MainActivity extends BaseActivity {


    private FloatingActionButton floatingActionButton;
    private NoteFragment noteFragment;
    private TargetFragment targetFragment;
    private Button btn_note;
    private Button btn_target;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        floatingActionButton = findViewById(R.id.edit_floatButton);
        btn_note = findViewById(R.id.note);
        btn_target = findViewById(R.id.target);
        noteFragment = new NoteFragment();
        targetFragment = new TargetFragment();
    }

    @Override
    protected void initData() {

        getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.frg_edit, noteFragment, null).commitAllowingStateLoss();

        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frg_edit, noteFragment, null).commitAllowingStateLoss();
            }
        });

        btn_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frg_edit, targetFragment, null).commitAllowingStateLoss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");
        Log.d("asdadk", title + content);
    }
}