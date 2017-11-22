package com.pineteree.meiriyijian.net;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/11/22.
 */

public interface Api {

    //http://gank.io/api/data/Android/10/1
    @GET("data/{category}/{pageSize}/{page}")
    Call<RequestBody> getCategoryData(@Path("category") String category,
                                      @Path("pageSize") String pageSize,
                                      @Path("page") String page);

}
