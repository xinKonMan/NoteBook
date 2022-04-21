package com.xinkon.notebook.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_HOME){
            return true;
        }else if(keyCode==KeyEvent.KEYCODE_BACK){
            /*Bundle bundle = new Bundle();
            bundle.putString("title",et_title.getText().toString());
            bundle.putString("content",et_content.getText().toString());
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("note");
            fragment.setArguments(bundle);*/
            Intent intent = new Intent();
            intent.putExtra("title",et_title.getText().toString());
            intent.putExtra("content",et_content.getText().toString());
            intent.putExtra("time",requestTime());
            //返回数据
            setResult(RESULT_OK,intent);
            //结束这个进程
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    //获取到当前时间
    public String requestTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    //在标题处按下回车直接跳转到内容框,文本监听器
    private class JumpTextWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if(str.contains("\r") || str.contains("\n")){
                //发现输入回车符和换行符，删除
                et_title.setText(str.replace("\r","").replace("\n",""));
                //获取焦点
                et_content.requestFocus();
                //若有内容则直接将光标跳转到文本末尾
                et_content.setSelection(et_content.getText().length());
            }
        }
    }

}