package com.pineteree.meiriyijian.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.pineteree.meiriyijian.R;
import com.pineteree.meiriyijian.common.Constant;
import com.pineteree.meiriyijian.home.model.GankModel;
import com.pineteree.meiriyijian.net.Api;
import com.pineteree.meiriyijian.net.HttpManager;
import com.pineteree.meiriyijian.widget.EmptyRecyclerView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.blankj.utilcode.util.NetworkUtils.isConnected;

/**
 * Created by Administrator on 2017/11/28.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    public Activity mContext;

    /**
     * 整个布局
     */
    private View mView;
    protected EmptyRecyclerView mRecyclerView;
    private Disposable mDisposable;
    protected List<GankModel.ResultsEntity> mList = new ArrayList<>();
    //空布局对象
    protected View mEmptyView;
    protected BaseAdapter mBaseAdapter;
    //刷新布局
    private SwipeRefreshLayout mRefreshLayout;
    //加载中动画
    private AVLoadingIndicatorView mAvi;
    //加载更多动画
    private AVLoadingIndicatorView mAviLoadMore;
    protected int mPage = 1;
    //加载更多布局
    private LinearLayout mLayoutLoadMore;

    /**
     * 是否可以加载更多
     */
    protected boolean mIsLoadMore = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initOptions(view);
        //设置布局类型
        mRecyclerView.setLayoutManager(initLayoutManager());
        //创建适配器
        mBaseAdapter = new BaseAdapter(mContext, mList, initItemType());
        mRecyclerView.setAdapter(mBaseAdapter);
        //设置空布局展示
        mRecyclerView.setmEmptyView(mEmptyView);
        //初次进入隐藏空布局展示，显示加载动画
        mRecyclerView.hideEmptyView();
        initItemListener();

        if (isConnected()) {
            showLoading();
            getDataFromServer(Constant.GET_DATA_TYPE_NOMAL);
        }


    }

    /**
     * 实现各自的业务操作，由子类实现
     *
     * @param view
     */
    protected void initOptions(View view) {

    }

    /**
     * 初始化显示类型
     *
     * @return
     */
    public int initItemType() {
        return Constant.ITEM_TYPE_TEXT;
    }

    protected void initItemListener() {
    }

    /**
     * 初始化列表的LayoutManager，默认提供线性
     *
     * @return
     */
    public RecyclerView.LayoutManager initLayoutManager() {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }


    /**
     * 从服务端请求数据
     *
     * @param type
     */
    private void getDataFromServer(final int type) {
        Api api = HttpManager.getInstance().getApiService();
        api.getCategoryData(getApiCategroy(), Constant.PAGE_SIZE, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull GankModel gankModel) {
                        if (gankModel.getError()) {
                            //服务端请求数据发生错误
                            ToastUtils.showShortSafe("服务端异常，请稍后再试");
                            return;
                        }
                        //更新界面数据
                        if (Constant.GET_DATA_TYPE_NOMAL == type) {
                            //正常模式下，清空之前数据，重新加载
                            mList.clear();
                            mList = gankModel.getResults();
                        } else {
                            //加载更多
                            mList.addAll(gankModel.getResults());
                        }
                        //如果获取的数据不足一页，代表当前已经没有更过数据，关闭加载更多
                        if (gankModel.getResults().size() < Constant.PAGE_SIZE) {
                            mIsLoadMore = false;
                        }
                        mBaseAdapter.setmListData(mList);
                        mBaseAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        stopRefresh();
                        hideLoading();
                        stopLoadingMore();
                    }

                    @Override
                    public void onComplete() {
                        stopRefresh();
                        hideLoading();
                        stopLoadingMore();
                    }
                });

    }

    public abstract String getApiCategroy();

    private void initView(View view) {
        mRecyclerView = (EmptyRecyclerView) view.findViewById(R.id.fragment_recyclerview);
        mEmptyView = view.findViewById(R.id.empty_view);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout);
        mAvi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);
        mAviLoadMore = (AVLoadingIndicatorView) view.findViewById(R.id.avi_loadmore);
        mLayoutLoadMore = (LinearLayout) view.findViewById(R.id.layout_loadmore);
        //设置下拉刷新样式颜色
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        //下拉刷新事件
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mIsLoadMore = true;
                getDataFromServer(Constant.GET_DATA_TYPE_NOMAL);
            }
        });
        //监听上拉加载更多
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollListener());

    }

    /**
     * 回滚到顶端
     */
    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void showLoading() {
        mAvi.smoothToShow();
    }

    @Override
    public void hideLoading() {
        if (mAvi.isShown()) {
            mAvi.smoothToHide();
        }
    }

    @Override
    public void stopRefresh() {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void startLoadingMore() {
        mLayoutLoadMore.setVisibility(View.VISIBLE);
        mAviLoadMore.smoothToShow();
    }

    @Override
    public void stopLoadingMore() {
        mLayoutLoadMore.setVisibility(View.GONE);
        mAviLoadMore.smoothToHide();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 退出的时候不在回调更新界面
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    /**
     * 滑动监听
     */
    class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            int lastPosition = -1;
            //当前状态位停止状态
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                    ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(lastPositions);
                    lastPosition = findMax(lastPositions);
                }
                //判断当前列表是否滑动到底部
                if (!mRecyclerView.canScrollVertically(1)) {
                    //滑动到底部，需要触发上拉加载更多操作
                    try {
                        mRecyclerView.smoothScrollToPosition(lastPosition);
                    } catch (IllegalArgumentException e) {
                        LogUtils.d("recyclerViewPosition",e.getMessage());
                    }
                    if (!mIsLoadMore) {
                        ToastUtils.showShort("没有更多数据了");
                        return;
                    }
                    startLoadingMore();
                    mPage++;
                    getDataFromServer(Constant.GET_DATA_TYPE_LOADMORE);
                }
            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
