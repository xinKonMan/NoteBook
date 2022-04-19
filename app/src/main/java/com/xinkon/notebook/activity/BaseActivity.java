package com.xinkon.notebook.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.xinkon.notebook.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        //直接一开始创建就调用这些方法
        //传入layout布局
        setContentView(initLayout());
        //负责初始化一些控件
        initView();
        //对事件的封装和监听事件，接口方法等
        initData();
    }
    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initData();

    //输出提示
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    //页面跳转
    public void navigateTo(Class cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }



    //跳转并返回数据
    public void navigateToWithResult(Class cls,int requestCode) {
        Intent intent = new Intent(mContext, cls);
        startActivityForResult(intent,requestCode);
    }

    //清除回退堆栈
    //Intent.FLAG_ACTIVITY_NEW_TASK创建一个新栈
    public void navigateToWithFlag(Class cls, int flags) {
        Intent intent = new Intent(mContext, cls);
        intent.setFlags(flags);
        startActivity(intent);
    }

    //轻量级存储数据
    protected void insertVal(String key, String val) {
        //轻量级存储，将token存进xml文件中
        SharedPreferences sharedPreferences = getSharedPreferences("sp_xinkon", MODE_PRIVATE);
        //编辑存储进去
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, val);
        editor.commit();
    }

    //获取到轻量级存储中的数据
    protected String findByKey(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("sp_xinkon", MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }


}