package com.chenli.retrofit2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author cl
 * @创建时间 2016/12/29 21:38
 * @描述 ${todo}
 * @版本 $$Rev$$
 * @更新者 $$Author$$
 */


public interface IService {
//    @GET("/tnfs/api/list")这是一般的请求地址
    @GET("/tnfs/{category}/list")
    Call<Tngou> getList(@Path("category") String category,@Query("page") int page, @Query("rows") int rows, @Query("id") int id);
}
