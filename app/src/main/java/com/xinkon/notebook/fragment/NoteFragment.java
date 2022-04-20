package com.xinkon.notebook.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xinkon.notebook.R;
import com.xinkon.notebook.activity.EditActivity;


public class NoteFragment extends BaseFragment {

    private FloatingActionButton floatingActionButton;


    public NoteFragment() {

    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initView() {
        floatingActionButton = mRootView.findViewById(R.id.edit_floatButton);
    }

    @Override
    protected void initData() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(EditActivity.class);
            }
        });
    }
}