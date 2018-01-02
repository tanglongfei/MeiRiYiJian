package com.pineteree.meiriyijian.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

/**
 * Created by Administrator on 2017/11/22.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        //数据库初始化
        Realm.init(this);
    }
}