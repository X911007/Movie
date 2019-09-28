package com.bw.movie.util;

import com.bw.movie.bean.BeanLogin;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * author: Xuexiandong
 * data: 2019/9/27 21:21:04
 * function：接口
 */
public interface IApi {
    //登录
    @FormUrlEncoded
    @POST(Api.URL_LOGIN)
    Observable<BeanLogin> getLogin(@FieldMap Map<String, String> hmap);
}
