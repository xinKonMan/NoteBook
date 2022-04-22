package com.xinkon.notebook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.xinkon.notebook.DateBase.NoteCRUD;
import com.xinkon.notebook.DateBase.NoteDateBase;
import com.xinkon.notebook.R;
import com.xinkon.notebook.fragment.NoteFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends BaseActivity {

    private EditText et_title;
    private EditText et_content;
    private TextView tv_time;

    private String old_title;
    private String old_content;
    private String old_time;
    private int openMode;
    private int old_tag;
    private long id;
    private int collect;
    private Intent intent;

    //标签是否被修改,默认没有被修改
    private boolean tagChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView() {
        et_title = findViewById(R.id.edit_title);
        et_content = findViewById(R.id.edit_content);
        tv_time = findViewById(R.id.edit_date);
    }

    @Override
    protected void initData() {
        et_content.requestFocus();
        //对标题进行事件监听
        et_title.addTextChangedListener(new JumpTextWatcher());

        tv_time.setText(requestTime());

        intent = getIntent();
        //获取到打开模式,获取到开启是那种形式，是新开的还是在旧内容修改
        openMode = intent.getIntExtra("openMode", 0);

        //打开已存在的note
        if (openMode == 3) {
            //存入id，方便修改之后返回对应id用于在修改是保证id前后一致
            id = intent.getLongExtra("id", 0);
            old_title = intent.getStringExtra("title");
            old_content = intent.getStringExtra("content");
            old_time = intent.getStringExtra("time");
            old_tag = intent.getIntExtra("tag", 1);
            collect = intent.getIntExtra("collection", 0);
            //放入旧数据
            tv_time.setText(old_time);
            et_title.setText(old_title);
            et_content.setText(old_content);
            //将光标跳转到文本末尾
            et_content.setSelection(old_content.length());

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            //根据不同情况填入数据
            autoSetMessage();
            //返回数据
            setResult(RESULT_OK, intent);
            //结束这个进程
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void autoSetMessage() {

        if (openMode == 4) { //说明是新开启的页面
            //如果编辑位置没有添加内容，则不返回数据到数据库添加笔记
            //-1表示没有创建note笔记,不用做任何处理
            if (et_content.getText().toString().length() == 0) {
                intent.putExtra("resultMode", -1);
            } else {
                //0表示新建笔记
                intent.putExtra("resultMode", 0);
                intent.putExtra("title", et_title.getText().toString());
                intent.putExtra("content", et_content.getText().toString());
                intent.putExtra("time", requestTime());
                intent.putExtra("tag", 1);
                intent.putExtra("collect", 0);
            }
        } else { //openMode是3，为修改页面
            if (et_title.getText().toString().equals(old_title) && et_content.getText().toString().equals(old_content)
                    && !tagChange) {
                //表示没有做任何修改
                intent.putExtra("resultMode", -1);
            } else {
                //1表示当前页面已经被修改
                intent.putExtra("resultMode", 1);
                intent.putExtra("content", et_content.getText().toString());
                intent.putExtra("title", et_title.getText().toString());
                intent.putExtra("time", requestTime());
                //返回当前笔记id，保持id前后一致
                intent.putExtra("id", id);
                intent.putExtra("tag", 1);
            }

        }
    }


    //获取到当前时间
    public String requestTime() {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    //在标题处按下回车直接跳转到内容框,文本监听器
    private class JumpTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if (str.contains("\r") || str.contains("\n")) {
                //发现输入回车符和换行符，删除
                et_title.setText(str.replace("\r", "").replace("\n", ""));
                //获取焦点
                et_content.requestFocus();
                //若有内容则直接将光标跳转到文本末尾
                et_content.setSelection(et_content.getText().length());
            }
        }
    }

}