package com.pineteree.meiriyijian_day01.main;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pineteree.meiriyijian_day01.R;
import com.pineteree.meiriyijian_day01.common.Constant;
import com.pineteree.meiriyijian_day01.home.HomeFragment;
import com.pineteree.meiriyijian_day01.main.model.DrawModel;
import com.pineteree.meiriyijian_day01.me.MeFragment;
import com.pineteree.meiriyijian_day01.read.ReadFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int NAVIGATION_HOME = 0;
    private final int NAVIGATION_READ = 1;
    private final int NAVIGATION_ME = 2;
    private ViewPager mVpMain;//主界面ViewPager
    private List<Fragment> mList;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;//侧滑菜单
    private BottomNavigationView mBottomNavigation;//底部导航
    private Menu mMenu;
    private RecyclerView mRvDrawList;
    private List<DrawModel> mListDrawModel;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new BottomNavigationView
            .OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mVpMain.setCurrentItem(NAVIGATION_HOME);
                    return true;
                case R.id.navigation_read:
                    mVpMain.setCurrentItem(NAVIGATION_READ);
                    return true;
                case R.id.navigation_me:
                    mVpMain.setCurrentItem(NAVIGATION_ME);
                    return true;
            }

            return false;
        }
    };
    private MainDrawListAdapter mMainDrawListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolbar();
        initFragment();
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), this, mList);
        mVpMain.setAdapter(mainAdapter);
        //左侧侧滑列表
        mRvDrawList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));
        mMainDrawListAdapter = new MainDrawListAdapter(this, initData());
        mRvDrawList.setAdapter(mMainDrawListAdapter);
        initListener();
    }

    private List<DrawModel> initData() {
        mListDrawModel = new ArrayList<>();
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_ios, Constant.CATEGORY_IOS));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_girl, Constant.CATEGORY_GIRL));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_client, Constant.CATEGORY_CLIENT));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_recommend, Constant
                .CATEGROY_RECOMMEND));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_app, Constant.CATEGORY_APP));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_resource, Constant
                .CATEGORY_EXPANDRESOURCE));
        mListDrawModel.add(new DrawModel(R.drawable.drawer_icon_video, Constant.CATEGORY_VIDEO));
        return mListDrawModel;
    }

    private void initFragment() {
        mList = new ArrayList<>();
        mList.add(new HomeFragment());
        mList.add(new ReadFragment());
        mList.add(new MeFragment());
    }

    private void initListener() {
        /**
         * VIewPager监听
         */
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case NAVIGATION_HOME:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case NAVIGATION_READ:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_read);
                        break;
                    case NAVIGATION_ME:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View content = mDrawer.getChildAt(0);
                //偏移
                int offset = (int) (drawerView.getWidth() * slideOffset);
                content.setTranslationX(offset);
                //缩放效果
                //content.setScaleX(1 - slideOffset * 0.1f);
                //content.setScaleY(1 - slideOffset * 0.1f);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        mMainDrawListAdapter.setmOnMainDrawClickListener(new MainDrawListAdapter
                .OnMainDrawClickListener() {

            @Override
            public void onClick(int position) {
                String categroy = Constant.CATEGORY_ALL;
                switch (mListDrawModel.get(position).getTitle()) {
                    case "iOS":
                        categroy = Constant.CATEGORY_IOS;
                        showCategoryInfo(categroy);
                        break;
                    case "前端":
                        categroy = Constant.CATEGORY_CLIENT;
                        showCategoryInfo(categroy);
                        break;
                    case "瞎推荐":
                        categroy = Constant.CATEGROY_RECOMMEND;
                        showCategoryInfo(categroy);
                        break;
                    case "App":
                        categroy = Constant.CATEGORY_APP;
                        showCategoryInfo(categroy);
                        break;
                    case "拓展资源":
                        categroy = Constant.CATEGORY_EXPANDRESOURCE;
                        showCategoryInfo(categroy);
                        break;
                    case "休息视频":
                        categroy = Constant.CATEGORY_VIDEO;
                        showCategoryInfo(categroy);
                        break;
                    case "福利":
                        categroy = Constant.CATEGORY_GIRL;
                        showCategoryInfo(categroy);
                        break;
                }
            }
        });
    }

    private void showCategoryInfo(String categroy) {
        Toast.makeText(this, "跳转到分类" + categroy, Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mVpMain = (ViewPager) findViewById(R.id.vp_main);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mDrawer = (DrawerLayout) findViewById(R.id.drawlayout);
        mRvDrawList = (RecyclerView) findViewById(R.id.recyclerview_drawerlist);
        mDrawer.setScrimColor(Color.TRANSPARENT);
        mDrawer.setDrawerElevation(0);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        private Context mContext;
        private List<Fragment> mList;

        private MainAdapter(FragmentManager fm, Context mContext, List<Fragment> mList) {
            super(fm);
            this.mList = mList;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }


        public void setmList(List<Fragment> mList) {
            this.mList = mList;
        }
    }

}
