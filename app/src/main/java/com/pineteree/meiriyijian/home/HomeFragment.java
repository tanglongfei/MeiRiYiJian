package com.pineteree.meiriyijian.home;

import android.content.Intent;

import com.pineteree.meiriyijian.detail.DetailActivity;
import com.pineteree.meiriyijian.base.BaseAdapter;
import com.pineteree.meiriyijian.base.BaseFragment;
import com.pineteree.meiriyijian.common.Constant;
import com.pineteree.meiriyijian.home.model.GankModel;

/**
 * Created by Administrator on 2017/11/20.
 */

public class HomeFragment extends BaseFragment {

    @Override
    public String getApiCategroy() {
        return Constant.CATEGORY_Android;
    }

    @Override
    protected void initItemListener() {
        mBaseAdapter.addOnClickListener(new BaseAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                //跳转到详情界面
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("entity",entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {

            }
        });
        super.initItemListener();
    }
}
