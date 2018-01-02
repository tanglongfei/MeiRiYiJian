package com.pineteree.meiriyijian.collect;

import android.content.Intent;

import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;


import com.pineteree.meiriyijian.R;
import com.pineteree.meiriyijian.base.BaseActivity;
import com.pineteree.meiriyijian.base.BaseAdapter;
import com.pineteree.meiriyijian.common.Constant;
import com.pineteree.meiriyijian.db.DbManager;
import com.pineteree.meiriyijian.db.SaveModel;
import com.pineteree.meiriyijian.detail.DetailActivity;
import com.pineteree.meiriyijian.home.model.GankModel;
import com.pineteree.meiriyijian.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends BaseActivity {
    private EmptyRecyclerView mRecyclerView;
    private List<SaveModel> mListSave;
    private BaseAdapter mBaseAdapter;
    private List<GankModel.ResultsEntity> mList = new ArrayList<>();

    @Override
    protected void initOptions() {
        //设置适配器，展示数据
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CollectActivity.this, LinearLayoutManager.VERTICAL, false));
        //加载数据库数据
        loadCollectionData();
        mBaseAdapter = new BaseAdapter(this, mList, Constant.ITEM_TYPE_TEXT);
        mRecyclerView.setAdapter(mBaseAdapter);
        mRecyclerView.setmEmptyView(findViewById(R.id.empty_view));
        mBaseAdapter.addOnClickListener(new BaseAdapter.OnBaseClickListener() {
            @Override
            public void onClick(int position, GankModel.ResultsEntity entity) {
                Intent intent = new Intent(CollectActivity.this, DetailActivity.class);
                String images = "";
                if (entity.getImages() != null && entity.getImages().size() > 0) {
                    images = entity.getImages().get(0);
                }
                intent.putExtra("entity", entity);
                startActivity(intent);
            }

            @Override
            public void onCoverClick(int position, GankModel.ResultsEntity entity) {

            }
        });

    }

    /**
     * 从数据库中查询收藏数据
     */
    private void loadCollectionData() {
        mListSave = DbManager.getInstence().queryAll(SaveModel.class);
        if (mListSave != null && mListSave.size() > 0) {
            initAdapterList(mListSave);
        }
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_download).setVisible(false);
        menu.findItem(R.id.action_share).setVisible(false);
    }

    /**
     * 数据对象转换
     */
    private void initAdapterList(List<SaveModel> listTemp) {
        mList.clear();
        for (SaveModel savemodel : listTemp) {
            List<String> images = new ArrayList<>();
            images.add(savemodel.getImages());
            GankModel.ResultsEntity entity = new GankModel.ResultsEntity(
                    savemodel.get_id(),
                    savemodel.getCreatedAt(),
                    savemodel.getDesc(),
                    savemodel.getPublishedAt(),
                    savemodel.getSource(),
                    savemodel.getType(),
                    savemodel.getUrl(),
                    savemodel.getUsed(),
                    savemodel.getWho(),
                    images);
            mList.add(entity);
        }
    }

    @Override
    public View initContentView() {
        View view = View.inflate(this, R.layout.activity_collect, null);
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.collection_recyclerview);
        return view;
    }

    @Override
    protected String initToolbarTitle() {
        return "已收藏";
    }
}
