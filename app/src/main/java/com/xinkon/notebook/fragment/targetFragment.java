package com.xinkon.notebook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.xinkon.notebook.R;


public class targetFragment extends Fragment {


    public targetFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static targetFragment newInstance() {
        targetFragment fragment = new targetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }
}