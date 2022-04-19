package com.xinkon.notebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xinkon.notebook.activity.BaseActivity;
import com.xinkon.notebook.activity.EditActivity;
import com.xinkon.notebook.fragment.NoteFragment;

public class MainActivity extends BaseActivity {


    private FloatingActionButton floatingActionButton;
    private NoteFragment noteFragment;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        floatingActionButton = findViewById(R.id.edit_floatButton);
    }

    @Override
    protected void initData() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToWithResult(EditActivity.class,1);
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