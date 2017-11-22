package com.pineteree.meiriyijian.net;

import com.pineteree.meiriyijian.home.model.GankModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/11/22.
 */

public interface Api {
    //http://gank.io/api/data/Android/10/1
    @GET("data/{category}/{pageSize}/{page}")
    Observable<GankModel> getCategoryData(@Path("category") String category,
                                          @Path("pageSize") int pageSize,
                                          @Path("page") int page);

}
