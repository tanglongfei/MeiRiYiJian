package com.pineteree.meiriyijian_day01.read;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pineteree.meiriyijian_day01.R;

/**
 * Created by Administrator on 2017/11/20.
 */

public class ReadFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read,container,false);
        return view;
    }
}
