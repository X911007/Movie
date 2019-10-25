package com.bw.movie.util;

import com.bw.movie.bean.BeanBanner;
import com.bw.movie.bean.BeanBuyMovieTickets;
import com.bw.movie.bean.BeanComingSoonMovie;
import com.bw.movie.bean.BeanEmail;
import com.bw.movie.bean.BeanFindAllMovieComment;
import com.bw.movie.bean.BeanFindMovieSchedule;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.bean.BeanFindNearbyCinemas;
import com.bw.movie.bean.BeanFindRecommendCinemas;
import com.bw.movie.bean.BeanFindSeatInfo;
import com.bw.movie.bean.BeanLogin;
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.bean.BeanPay;
import com.bw.movie.bean.BeanRegistered;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.bean.BeanWeChatBindingLogin;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author: Xuexiandong
 * data: 2019/9/27 21:21:04
 * function：接口
 */
public interface IApi {
    //登录
    @FormUrlEncoded
    @POST(Api.URL_LOGIN)
    Observable<BeanLogin> getLogin(@FieldMap Map<String, String> loginmap);

    //微信登录
    @FormUrlEncoded
    @POST(Api.URL_WECHATBINDINGLOGIN)
    Observable<BeanWeChatBindingLogin> getWeChatBindingLogin(@FieldMap Map<String, String> weloginmap);

    //注册
    @FormUrlEncoded
    @POST(Api.URL_REGISTER)
    Observable<BeanRegistered> getRegistered(@FieldMap Map<String, String> registeredmap);

    //发送邮箱验证码
    @FormUrlEncoded
    @POST(Api.URL_EMAIL_E)
    Observable<BeanEmail> getEmail(@FieldMap Map<String, String> emailmap);

    //Banner
    @GET(Api.URL_BANNER)
    Observable<BeanBanner> getBanner();

    //正在热映
    @GET(Api.URL_RELEASEMOVIELIST)
    Observable<BeanReleaseMovie> getReleaseMovie(@QueryMap Map<String, Integer> releasemap);

    //即将上映
    @GET(Api.URL_COMINGSOONMOVIE)
    Observable<BeanComingSoonMovie> getComingSoonMovie(@HeaderMap Map<String, String> hmap,@QueryMap Map<String, Integer> qmap);

    //热门电影
    @GET(Api.URL_HOTMOVIE)
    Observable<BeanHotMovie> getHotMovie(@QueryMap Map<String, Integer> Popularmap);

    //电影详情页
    @GET(Api.URL_FINDMOVIESDETAIL)
    Observable<BeanFindMoviesDetail> getFindMoviesDetail(@HeaderMap Map<String, String> hmap, @QueryMap Map<String, Integer> qmap);

    //电影评论
    @GET(Api.URL_FINDALLMOVIECOMMENT)
    Observable<BeanFindAllMovieComment> getFindAllMovieComment(@HeaderMap Map<String, String> hmap, @QueryMap Map<String, Integer> qmap);

    //推荐影院
    @GET(Api.URL_FINDRECOMMENDCINEMAS)
    Observable<BeanFindRecommendCinemas> getFindRecommendCinemas(@HeaderMap Map<String, String> hmap, @QueryMap Map<String, Integer> qmap);

    //根据电影ID和影院ID查询电影排期列表
    @GET(Api.URL_FINDMOVIESCHEDULE)
    Observable<BeanFindMovieSchedule> getFindMovieSchedule(@QueryMap Map<String, Integer> qmap);

    //查询附近影院
    @GET(Api.URL_FINDNEARBYCINEMAS)
    Observable<BeanFindNearbyCinemas> getFindNearbyCinemas(@HeaderMap Map<String, String> hmap, @QueryMap Map<String, Object> qmap);

   //根据影厅id 查询座位信息
    @GET(Api.URL_FINDSEATINFO)
    Observable<BeanFindSeatInfo> getFindSeatInfo(@QueryMap Map<String, Integer> qmap);

    //购票下单
    @FormUrlEncoded
    @POST(Api.URL_BUYMOVIETICKETS)
    Observable<BeanBuyMovieTickets> getBuyMovieTickets(@HeaderMap Map<String, String> hmap,@FieldMap Map<String, Object> loginmap);

    //支付
    @FormUrlEncoded
    @POST(Api.URL_PAY)
    Observable<BeanPay> getPay(@HeaderMap Map<String, String> hmap, @FieldMap Map<String, Object> zmap);

}
