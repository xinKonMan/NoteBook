package com.xinkon.notebook.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xinkon.notebook.DateBase.NoteCRUD;
import com.xinkon.notebook.MainActivity;
import com.xinkon.notebook.R;
import com.xinkon.notebook.activity.EditActivity;
import com.xinkon.notebook.adapter.EditAdapter;
import com.xinkon.notebook.entity.Note;

import java.util.ArrayList;
import java.util.List;


public class NoteFragment extends BaseFragment {

    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private EditAdapter editAdapter;
    private List<Note> notes = new ArrayList<>();



    public NoteFragment() {

    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_note;
    }

    @Override
    protected void initView() {
        floatingActionButton = mRootView.findViewById(R.id.edit_floatButton);
        recyclerView = mRootView.findViewById(R.id.rec_edit);

    }

    @Override
    protected void initData() {
        //创建新的笔记
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditActivity.class);
                //创建一个新的EditActivity的开启模式为4
                intent.putExtra("openMode",4);
                startActivityForResult(intent,1);
            }
        });

        NoteCRUD db = new NoteCRUD(getActivity());
        //创建适配器
        editAdapter = new EditAdapter(getContext(),notes);
        //刷新数据
        refreshRecycleView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(editAdapter);
        //在设置适配器之后调用item点击事件
        editAdapter.setOnItemClickLitener(new EditAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                //跳转到EditActivity,并将数据传到EditActivity中
                Intent intent = new Intent(getActivity(),EditActivity.class);
                intent.putExtra("title",note.getTitle());
                intent.putExtra("content",note.getContent());
                intent.putExtra("time",note.getTime());
                intent.putExtra("id",note.getId());
                //3代表可修改模式。表示打开已经存在的note笔记
                intent.putExtra("openMode",3);
                intent.putExtra("tag",note.getTag());
                intent.putExtra("collect",note.getCollect());
                startActivityForResult(intent,1);
            }
        });


    }


    //刷新数据
    public void refreshRecycleView() {
        //调用数据库
        NoteCRUD db = new NoteCRUD(getActivity());
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