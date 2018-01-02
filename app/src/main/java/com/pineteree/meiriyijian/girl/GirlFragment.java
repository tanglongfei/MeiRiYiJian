package com.pineteree.meiriyijian.girl;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pineteree.meiriyijian.R;
import com.pineteree.meiriyijian.base.BaseAdapter;
import com.pineteree.meiriyijian.base.BaseFragment;
import com.pineteree.meiriyijian.common.Constant;
import com.pineteree.meiriyijian.home.model.GankModel;
import com.pineteree.meiriyijian.showpic.ShowPicActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/5.
 */

public class GirlFragment extends BaseFragment implements View.OnClickListener {
    private FloatingActionButton mItemLinearlayout;
    private FloatingActionButton mItemGridlayout;
    private FloatingActionButton mItemStaggeredlayout;
    private FloatingActionMenu mActionMenu;

    private int mCurrentType = 2;
    private final int TYPE_LINEARLAYOUT = 1;
    private final int TYPE_GRIDLAYOUT = 2;
    private final int TYPE_STAGGEREDLAYOUT = 3;

    @Override
    public String getApiCategroy() {
        return "福利";
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
    }

    @Override
    public int initItemType() {
        return Constant.ITEM_TYPE_IMAGE;
    }

    @Override
    protected void initOptions(View view) {

        mItemLinearlayout = (FloatingActionButton) view.findViewById(R.id.menu_item_linearlayout);
        mItemGridlayout = (FloatingActionButton) view.findViewById(R.id.menu_item_gridlayout);
        mItemStaggeredlayout = (FloatingActionButton) view.findViewById(R.id
                .menu_item_staggeredlayout);
        mActionMenu = (FloatingActionMenu) view.findViewById(R.id.actionmenu);

        mActionMenu.setVisibility(View.VISIBLE);
        mItemLinearlayout.setOnClickListener(this);
        mItemGridlayout.setOnClickListener(this);
        mItemStaggeredlayout.setOnClickListener(this);

    }

    @Override
    protected void initItemListener() {
        mBaseAdapter.addOnClickListener(new BaseAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity resultsBean) {
                //跳转界面，显示大图
                Intent intent = new Intent(mContext, ShowPicActivity.class);
                //传入参数，图片URL地址
                ArrayList<String> listPics = new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    listPics.add(mList.get(i).getUrl());
                }
                intent.putStringArrayListExtra("picList", listPics);
                intent.putExtra("position", position);
                intent.putExtra("page", mPage);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_item_linearlayout:
                if (mCurrentType == TYPE_LINEARLAYOUT) {
                    return;
                }
                mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,
                        LinearLayoutManager.VERTICAL, false));
                mActionMenu.close(true);
                mCurrentType = TYPE_LINEARLAYOUT;
                break;
            case R.id.menu_item_gridlayout:
                if (mCurrentType == TYPE_GRIDLAYOUT) {
                    return;
                }
                mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2,
                        GridLayoutManager.VERTICAL, false));
                mActionMenu.close(true);
                mCurrentType = TYPE_GRIDLAYOUT;
                break;
            case R.id.menu_item_staggeredlayout:
                if (mCurrentType == TYPE_STAGGEREDLAYOUT) {
                    return;
                }
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mBaseAdapter.setmIsStaggered(true);
                mActionMenu.close(true);
                mCurrentType = TYPE_STAGGEREDLAYOUT;
                break;
        }
    }
}
