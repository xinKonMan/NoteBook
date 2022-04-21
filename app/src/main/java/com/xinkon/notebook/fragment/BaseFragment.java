package com.xinkon.notebook.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xinkon.notebook.R;
import com.xinkon.notebook.activity.EditActivity;

/**
 * @author xinkonman
 * @desription:
 * @date: 2022/4/20 22:32
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //视图为空时,根据initLayout创建该view
        if (mRootView == null) {
            mRootView = inflater.inflate(initLayout(), container, false);
            initView();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        initData();
    }

    protected abstract int initLayout();

    //存储控件对象的定义
    protected abstract void initView();

    protected abstract void initData();


    //提示信息
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    //在子线程中也可以使用,异步处理
    public void showToastSync(String msg) {
        Looper.prepare();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    //跳转并返回数据
    public void navigateToWithResult(Class cls,int requestCode) {
        Intent intent = new Intent(mContext, cls);
        startActivityForResult(intent,requestCode);
    }

    //跳转
    public void navigateTo(Class cls) {
        Intent intent = new Intent(mContext, cls);
        startActivity(intent);
    }



}
