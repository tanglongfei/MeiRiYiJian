package com.pineteree.meiriyijian.net;

import com.pineteree.meiriyijian.home.model.GankModel;
import com.pineteree.meiriyijian.read.model.ReadModel;

import io.reactivex.Completable;
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

    @GET("api/v1/info/{page}/{pageSize}")
    Observable<ReadModel> getReadData(@Path("page") int page,
                                      @Path("pageSize") int pageSize);

    @GET("search/query/{searchkeyword}/category/all/count/10/page/{page}")
    Observable<GankModel> getSearchData(@Path("searchkeyword") String searchkeyword,
                              @Path("page") int page);
}
