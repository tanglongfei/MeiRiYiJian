package com.pineteree.meiriyijian.othercategory;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

import com.pineteree.meiriyijian.R;
import com.pineteree.meiriyijian.base.BaseActivity;

public class OtherCategoryActivity extends BaseActivity {

    private View mView;
    private String mTtitleCategroy;
    private Fragment mFragment;

    @Override
    public View initContentView() {
        mView = View.inflate(this, R.layout.activity_other_category,null);
        return mView;
    }

    @Override
    protected void initOptions() {
        mTtitleCategroy = getIntent().getStringExtra("categroy");
        //展示具体的数据
        mFragment = OtherCategroyFragment.newInstence(mTtitleCategroy);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, mFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected String initToolbarTitle() {
        return mTtitleCategroy;
    }

    @Override
    protected void updateOptionsMenu(Menu menu) {
        super.updateOptionsMenu(menu);
        menu.findItem(R.id.action_share).setVisible(false);
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_download).setVisible(false);
    }
}
