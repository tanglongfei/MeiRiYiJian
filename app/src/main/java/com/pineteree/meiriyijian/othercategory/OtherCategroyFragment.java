package com.pineteree.meiriyijian.othercategory;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import com.pineteree.meiriyijian.DetailActivity;
import com.pineteree.meiriyijian.base.BaseAdapter;
import com.pineteree.meiriyijian.base.BaseFragment;
import com.pineteree.meiriyijian.home.model.GankModel;


/**
 * @author Leon
 * @date
 */

public class OtherCategroyFragment extends BaseFragment {
    private static final String CATEGROY = "categroy";

    public static Fragment newInstence(String tag) {
        OtherCategroyFragment otherCategroyFragment = new OtherCategroyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGROY, tag);
        otherCategroyFragment.setArguments(bundle);
        return otherCategroyFragment;
    }


    @Override
    public String getApiCategroy() {
        return getArguments().getString(CATEGROY);
    }

    @Override
    protected void initItemListener(BaseAdapter baseAdapter) {
        baseAdapter.addOnClickListener(new BaseAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                //跳转到详情界面
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("entity", entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {

            }
        });
        super.initItemListener(baseAdapter);
    }
}
