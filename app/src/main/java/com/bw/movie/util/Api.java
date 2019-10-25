package com.bw.movie.util;

/**
 * author: Xuexiandong
 * data: 2019/9/27 21:21:05
 * function：接口
 */
public interface Api {
    //Base
    String URL_BASE="http://mobile.bwstudent.com/";
    String URL_BASE_="http://172.17.8.100/";

    //登录
    String URL_LOGIN="movieApi/user/v2/login";
    //发送验证码
    String URL_EMAIL_E="movieApi/user/v2/sendOutEmailCode";
    //注册
    String URL_REGISTER="movieApi/user/v2/register";
    //Banner
    String URL_BANNER="movieApi/tool/v2/banner";
    //正在热映
    String URL_RELEASEMOVIELIST="movieApi/movie/v2/findReleaseMovieList";
    //即将上映
    String URL_COMINGSOONMOVIE="movieApi/movie/v2/findComingSoonMovieList";
    //热门电影
    String URL_HOTMOVIE="movieApi/movie/v2/findHotMovieList";
    //电影详情页
    String URL_FINDMOVIESDETAIL="movieApi/movie/v2/findMoviesDetail";
    //电影评论
    String URL_FINDALLMOVIECOMMENT="movieApi/movie/v2/findAllMovieComment";
    //推荐影院
    String URL_FINDRECOMMENDCINEMAS="movieApi/cinema/v1/findRecommendCinemas";
    //根据电影ID和影院ID查询电影排期列表
    String URL_FINDMOVIESCHEDULE="movieApi/movie/v2/findMovieSchedule";
    //查询附近影院
    String URL_FINDNEARBYCINEMAS="movieApi/cinema/v1/findNearbyCinemas";
    //根据影厅id 查询座位信息
    String URL_FINDSEATINFO="movieApi/movie/v2/findSeatInfo";
    //购票下单
    String URL_BUYMOVIETICKETS="movieApi/movie/v2/verify/buyMovieTickets";
    //微信登录
    String URL_WECHATBINDINGLOGIN="movieApi/user/v1/weChatBindingLogin";
    //支付
    String URL_PAY="movieApi/movie/v2/verify/pay";

    //账号
    String URL_EMAIL_S="1229863813@qq.com";
    //密码
    String URL_PWD_S="eWLPHopE945d2ivttHaQTQ==";

    //email
    String URL_EMAIL="email";
    //pwd
    String URL_PWD="pwd";
    //昵称
    String URL_NICKNAME="nickName";
    //验证码
    String URL_CODE="code";
    //页数
    String URL_PAGE="page";
    //一页几条
    String URL_COUNT="count";
    //userId
    String URL_USERID="userId";
    String URL_USERID_S="13679";
    //sessionId
    String URL_SESSIONID="sessionId";
    String URL_SESSIONID_S="157071374447513679";
    //电影ID
    String URL_MOVIEID="movieId";
    //影厅ID
    String URL_HALLID="hallId";
    //经度
    String URL_LONGITUDE="longitude";
    //纬度
    String URL_LATITUDE="latitude";
    //影院ID
    String URL_CINEMAID="cinemaId";
    //微信    AppID
    String WX_APP_ID="wxb3852e6a6b7d9516";
    //纬度
    String URL_LATITUDE_S="40.0404";
    //经度
    String URL_LONGITUDE_S="116.299566";

    //纬度
    String URL_LATITUDE_D="40.040576";
    //经度
    String URL_LONGITUDE_D="116.299928";
    //排期表id
    String URL_SCHEDULEID="scheduleId";
    //座位
    String URL_SANQI="seat";
    //签名
    String URL_SIGN="sign";

    //SharedPreferences
    String SP_SP="sp";
    //userId
    String SP_USERID="userId";
    //sessionId
    String SP_SESSIONID="sessionId";
    //账号
    String SP_NAME="name";
    //密码
    String SP_PWD="pwd";
    //Identification
    String SP_IDENTIFICATION="pIdentificationwd";
    //code
    String SP_CODE="code";
    //支付类型
    String SP_PAYTYPE="payType";
    //订单号
    String SP_ORDERID="orderId";
    //首次
    String SP_FIRST="first";







}
