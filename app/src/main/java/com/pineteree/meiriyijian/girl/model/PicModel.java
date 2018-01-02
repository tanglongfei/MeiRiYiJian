package com.pineteree.meiriyijian.girl.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author tlf
 * @date
 */

public class PicModel extends RealmObject implements Serializable {
    @PrimaryKey
    private String _id;

    private int height;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
