package com.pineteree.meiriyijian.search;

import com.pineteree.meiriyijian.base.BaseView;
import com.pineteree.meiriyijian.home.model.GankModel;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public interface SearchContract {
    interface View extends BaseView {
        /**
         * 给搜索框显示搜索条件
         *
         * @param msg
         */
        void setEditText(String msg);

        /**
         * 是否显示搜索结果，显示搜索结果事需要隐藏掉热门搜索和历史搜索
         *
         * @param flag
         */
        void showSearchResult(boolean flag);

        /**
         * 更新界面数据
         */
        void updateShow(GankModel gankModel, int type);

        /**
         * 显示错误信息
         *
         * @param msg
         */
        void showErrorTip(String msg);

        void setPrestener();
    }

    interface Prestener {
        /**
         * 加载历史搜索记录数据
         *
         * @return
         */
        List<String> loadHistroyData();

        /**
         * 加载热门搜索数据
         *
         * @return
         */
        List<String> loadHotTag();

        /**
         * 添加历史搜索记录
         *
         * @param keyword
         */
        void addHistorySearch(String keyword);

        /**
         * 清除所有历史记录
         *
         * @param tag
         */
        void clearAllHistory(String tag);

        /**
         * 服务端获取搜索数据
         *
         * @param keyword 关键字
         * @param page
         * @param type
         */
        void getDataFromService(String keyword, int page, int type);

        /**
         * 根据搜索条件从服务端查询数据
         *
         * @param keyword
         */
        void searchFromServer(String keyword);

        /**
         * 历史记录单击事件
         *
         * @param keyword
         */
        void historyClick(String keyword);
    }

}
