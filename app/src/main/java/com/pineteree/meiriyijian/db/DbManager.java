package com.pineteree.meiriyijian.db;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * 数据库操作
 *
 * @author tlf
 * @date
 */

public class DbManager<T extends RealmObject> {
    private static DbManager dbManager;
    private Realm realm;

    private DbManager() {

    }

    public static DbManager getInstence() {
        if (dbManager == null) {
            dbManager = new DbManager();
        }
        return dbManager;
    }

    /**
     * 保存信息到数据库
     * 插入数据
     */
    public void save(Object object) {
        RealmConfiguration config0 = new RealmConfiguration.Builder()
                .name("default0").schemaVersion(0).build();
        realm = Realm.getInstance(config0);
        realm.beginTransaction();
        if (object instanceof RealmObject) {
            realm.copyToRealm((RealmModel) object);
        }
        realm.commitTransaction();
    }

    /**
     * 查询所有数据
     *
     * @param clazz
     */
    public List<T> queryAll(Class<T> clazz) {
        RealmConfiguration config0 = new RealmConfiguration.Builder()
                .name("default0").schemaVersion(0).build();
        realm = Realm.getInstance(config0);
        realm.beginTransaction();
        RealmResults<T> realmResults = realm.where(clazz).findAll();
        realm.commitTransaction();
        return realmResults;
    }

    /**
     * 删除数据
     */
    public void cancelSave(Class<T> clazz, String _id) {
        RealmConfiguration config0 = new RealmConfiguration.Builder()
                .name("default0").schemaVersion(0).build();
        realm = Realm.getInstance(config0);
        realm.beginTransaction();
        T result = realm.where(clazz).equalTo("_id", _id).findFirst();
        result.deleteFromRealm();
        realm.commitTransaction();
    }

    /**
     * 查询单个数据
     *
     * @param clazz
     * @param _id
     * @return
     */
    public T queryModel(Class<T> clazz, String _id) {
        RealmConfiguration config0 = new RealmConfiguration.Builder()
                .name("default0").schemaVersion(0).build();
        realm = Realm.getInstance(config0);
        realm.beginTransaction();
        T result = realm.where(clazz).equalTo("_id", _id).findFirst();
        realm.commitTransaction();
        return result;
    }

}
