package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.MoviedetailspageActivity;
import com.bw.movie.activity.VideoSearchPageActivity;
import com.bw.movie.adapter.HotMovieRecyclerView;
import com.bw.movie.adapter.HotRecyclerView;
import com.bw.movie.adapter.ReleasedRecyclerView;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanBanner;
import com.bw.movie.bean.BeanComingSoonMovie;
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.contract.Contract;
import com.bw.movie.databean.DataBeanMovie;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.AMapLocationUtil;
import com.bw.movie.util.Api;
import com.bw.movie.util.RxJavaUtil;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：电影
 */
public class FragmentMovie extends BaseFragment implements Contract.HomeView {

    @BindView(R.id.Positioning)
    ImageView mPositioning;
    @BindView(R.id.Positioning_text)
    TextView mPositioningText;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.xeanner)
    XBanner mXeanner;
    @BindView(R.id.Hot_)
    ImageView mHot_;
    @BindView(R.id.Hot)
    RelativeLayout mHot;
    /*@BindView(R.id.xRecyclerView)
    XRecyclerView mXRecyclerView;*/
    @BindView(R.id.Hot__)
    ImageView mHot__;
    @BindView(R.id.Coming)
    RelativeLayout mComing;
    Unbinder unbinder;
    @BindView(R.id.xRecyclerView)
    RecyclerView mXRecyclerView;
    @BindView(R.id.releasedRecyclerView)
    RecyclerView mReleasedRecyclerView;
    @BindView(R.id.popularRecyclerView)
    RecyclerView mPopularRecyclerView;
    @BindView(R.id.Popular)
    RelativeLayout mPopular;
    private Presenter presenter;
    private List<String> listBanner = new ArrayList<>();
    private static final String TAG = "FragmentMovie";
    private Map<String, Integer> qmap = new HashMap<>();
    private Map<String, String> hmap = new HashMap<>();
    private Map<String, Integer> qqmap = new HashMap<>();
    private DataBeanMovie dataBeanMovie =new DataBeanMovie();

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_movie_layout;
    }


    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    //点击（更多）
    @OnClick({R.id.Positioning, R.id.Positioning_text, R.id.search, R.id.Hot, R.id.Coming, R.id.Popular})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Positioning:
                Toast.makeText(getContext(), "已定位", Toast.LENGTH_SHORT).show();
                //定位方法
                AMapLocationUtil.getLoaction(new AMapLocationUtil.AMapInterface() {
                    @Override
                    public void getAMapLocation(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            if (aMapLocation.getErrorCode() == 0) {
                                //可在其中解析amapLocation获取相应内容。
                                //城市
                                String city = aMapLocation.getCity();
                                //区
                                String district = aMapLocation.getDistrict();
                                //街道
                                String street = aMapLocation.getStreet();
                                //街号
                                String streetNum = aMapLocation.getStreetNum();
                                Log.i(TAG, "111111111getAMapLocation: " + aMapLocation.getCity());
                                Toast.makeText(getContext(), "" + city + district + street + streetNum, Toast.LENGTH_SHORT).show();
                                mPositioningText.setText(city + district + street + streetNum);
                            } else {
                                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                                Log.e("AmapError", "location Error, ErrCode:"
                                        + aMapLocation.getErrorCode() + ", errInfo:"
                                        + aMapLocation.getErrorInfo());
                            }
                        }
                    }
                });
                break;
            case R.id.search:
                Toast.makeText(getContext(), "已搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Hot:
                Toast.makeText(getContext(), "正在热映（更多）", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), VideoSearchPageActivity.class));
                break;
            case R.id.Coming:
                Toast.makeText(getContext(), "即将上映（更多）", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), VideoSearchPageActivity.class));
                break;
            case R.id.Popular:
                Toast.makeText(getContext(), "热门电影（更多）", Toast.LENGTH_SHORT).show();
                getContext().startActivity(new Intent(getContext(), VideoSearchPageActivity.class));
                break;
        }
    }


    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
        boolean net = RxJavaUtil.getInstance().getNet(getContext());
        //网络判断
        if (!net) {
            Toast.makeText(getContext(), "无网", Toast.LENGTH_SHORT).show();
            //正在热映
            List<DataBeanMovie> list = RxJavaUtil.getInstance().getDaoSession().getDataBeanMovieDao().queryBuilder().list();
            Log.i(TAG, "11111initData: " + list.size());
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    //Banner
                    String jsonBanner = list.get(i).getJsonBanner();
                    //解析
                    BeanBanner beanBanner = new Gson().fromJson(jsonBanner, BeanBanner.class);
                    //取出图片
                    List<BeanBanner.ResultBean> listBann = beanBanner.getResult();
                    for (int i1 = 0; i1 < listBann.size(); i1++) {
                        String imageUrl = listBann.get(i1).getImageUrl();
                        listBanner.add(imageUrl);
                    }
                    //banner绑定数据
                    mXeanner.setData(listBanner, null);
                    //设置数据
                    mXeanner.setmAdapter(new XBanner.XBannerAdapter() {
                        @Override
                        public void loadBanner(XBanner banner, Object model, View view, int position) {
                            Glide.with(getContext())
                                    .load(listBanner.get(position))
                                    .error(R.drawable.jiaban)
                                    .placeholder(R.drawable.afanti)
                                    .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                                    .into((ImageView) view);
                        }
                    });
                    //切换特效
//                  mXeanner.setPageTransformer(Transformer.Accordion);
                    mXeanner.setPageTransformer(Transformer.Depth);
                    //播放间隔
                    mXeanner.setmAutoPalyTime(2000);
                    //隐藏指示器
                    mXeanner.setPointsIsVisible(false);
                    //点击
                    mXeanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(XBanner banner, int position) {
                            Toast.makeText(getContext(), "以点击     " + position, Toast.LENGTH_SHORT).show();
                        }
                    });
                    //开始轮播
                    mXeanner.startAutoPlay();
                    //正在热映
                    String jsonReleaseMovie = list.get(i).getJsonReleaseMovie();
                    BeanReleaseMovie beanReleaseMovie = new Gson().fromJson(jsonReleaseMovie, BeanReleaseMovie.class);
                    List<BeanReleaseMovie.ResultBean> beanReleaseMovieResult = beanReleaseMovie.getResult();
                    //设置适配器
//                  HotXRecyclerView hotXRecyclerView = new HotXRecyclerView(beanReleaseMovielist,getContext());
                    HotRecyclerView hotXRecyclerView = new HotRecyclerView(beanReleaseMovieResult, getContext());
                    //线性布局
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    //加载线性布局
                    mXRecyclerView.setLayoutManager(linearLayoutManager);
                    //设置适配器
                    mXRecyclerView.setAdapter(hotXRecyclerView);
                    //即将上映
                    String jsonComingSoonMovie = list.get(i).getJsonComingSoonMovie();
                    BeanComingSoonMovie beanComingSoonMovie = new Gson().fromJson(jsonComingSoonMovie, BeanComingSoonMovie.class);
                    List<BeanComingSoonMovie.ResultBean> beanComingSoonMovieResult = beanComingSoonMovie.getResult();
                    //设置适配器
                    ReleasedRecyclerView releasedRecyclerView = new ReleasedRecyclerView(beanComingSoonMovieResult, getContext());
                    //线性布局
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
                    linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
                    //加载线性布局
                    mReleasedRecyclerView.setLayoutManager(linearLayoutManager1);
                    //设置适配器
                    mReleasedRecyclerView.setAdapter(releasedRecyclerView);
                    //热门电影
                    String jsonHotMovie = list.get(i).getJsonHotMovie();
                    BeanHotMovie beanHotMovie = new Gson().fromJson(jsonHotMovie, BeanHotMovie.class);
                    List<BeanHotMovie.ResultBean> beanHotMovieResult = beanHotMovie.getResult();
                    //设置适配器
                    HotMovieRecyclerView hotMovieRecyclerView = new HotMovieRecyclerView(beanHotMovieResult, getContext());
                    //线性布局
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
                    linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                    //加载线性布局
                    mPopularRecyclerView.setLayoutManager(linearLayoutManager2);
                    //设置适配器
                    mPopularRecyclerView.setAdapter(hotMovieRecyclerView);
                }
            }
            return;
        }
        Toast.makeText(getContext(), "有网", Toast.LENGTH_SHORT).show();
        //清空表
        RxJavaUtil.getInstance().getDaoSession().getDataBeanMovieDao().deleteAll();
        //轮播图
        presenter.getBanner();
        //正在热映
        qmap.put(Api.URL_PAGE, 1);
        qmap.put(Api.URL_COUNT, 5);
        presenter.getReleaseMovie(qmap);
        //即将上映
        hmap.put(Api.URL_USERID, Api.URL_USERID_S);
        hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);
        presenter.getComingSoonMovie(hmap, qmap);
        //热门电影
        qqmap.put(Api.URL_PAGE, 1);
        qqmap.put(Api.URL_COUNT, 3);
        presenter.getHotMovie(qqmap);

    }

    //成功
    @Override
    public void onSuccess(Object object) {
        //轮播图
        if (object instanceof BeanBanner) {
            BeanBanner beanBanner = (BeanBanner) object;
            //转成json
            String jsonBanner = new Gson().toJson(beanBanner);
            //存入表
            dataBeanMovie.setJsonBanner(jsonBanner);
            List<BeanBanner.ResultBean> list = beanBanner.getResult();
            for (int i = 0; i < beanBanner.getResult().size(); i++) {
                String imageUrl = list.get(i).getImageUrl();
                listBanner.add(imageUrl);
            }
            //banner绑定数据
            mXeanner.setData(listBanner, null);
            //设置数据
            mXeanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getContext())
                            .load(listBanner.get(position))
                            .error(R.drawable.jiaban)
                            .placeholder(R.drawable.afanti)
                            .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(30, 0)))
                            .into((ImageView) view);
                }
            });
            //切换特效
//            mXeanner.setPageTransformer(Transformer.Accordion);
            mXeanner.setPageTransformer(Transformer.Depth);
            //播放间隔
            mXeanner.setmAutoPalyTime(2000);
            //隐藏指示器
            mXeanner.setPointsIsVisible(false);
            //点击
            mXeanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, int position) {
                    Toast.makeText(getContext(), "以点击     " + position, Toast.LENGTH_SHORT).show();
                }
            });
            //开始轮播
            mXeanner.startAutoPlay();
            //正在热映
        } else if (object instanceof BeanReleaseMovie) {
            BeanReleaseMovie beanReleaseMovie = (BeanReleaseMovie) object;
            //解析
            String jsonReleaseMovie = new Gson().toJson(beanReleaseMovie);
            //存入表
            dataBeanMovie.setJsonReleaseMovie(jsonReleaseMovie);
            //转成list
            List<BeanReleaseMovie.ResultBean> beanReleaseMovielist = beanReleaseMovie.getResult();
            Log.i(TAG, "onSuccess: " + beanReleaseMovielist.get(0).getName());
            //设置适配器
//            HotXRecyclerView hotXRecyclerView = new HotXRecyclerView(beanReleaseMovielist,getContext());
            HotRecyclerView hotXRecyclerView = new HotRecyclerView(beanReleaseMovielist, getContext());
            //线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //加载线性布局
            mXRecyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            mXRecyclerView.setAdapter(hotXRecyclerView);
            //点击购票
            hotXRecyclerView.setCallBackTickets(new HotRecyclerView.CallBackTickets() {
                @Override
                public void onTickets(BeanReleaseMovie.ResultBean bean) {
                    Toast.makeText(getContext(), "已购买" + bean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            //点击条目
            hotXRecyclerView.setCallBackTickets(new HotRecyclerView.CallBackTickets() {
                @Override
                public void onTickets(BeanReleaseMovie.ResultBean bean) {
                    Toast.makeText(getContext(), "" + bean.getMovieId(), Toast.LENGTH_SHORT).show();
                    //EventBus传值
                    EventBus.getDefault().postSticky(bean);
                    //跳转
                    getActivity().startActivity(new Intent(getContext(), MoviedetailspageActivity.class));
                }
            });
            //即将上映
        } else if (object instanceof BeanComingSoonMovie) {
            BeanComingSoonMovie beanComingSoonMovie = (BeanComingSoonMovie) object;
            String jsonComingSoonMovie = new Gson().toJson(beanComingSoonMovie);
            //存入表
            dataBeanMovie.setJsonComingSoonMovie(jsonComingSoonMovie);
            //转成list
            List<BeanComingSoonMovie.ResultBean> beanComingSoonMovielist = beanComingSoonMovie.getResult();
            Log.i(TAG, "onSuccess:11111 " + beanComingSoonMovie.getResult().get(0).getName());
            //设置适配器
            ReleasedRecyclerView releasedRecyclerView = new ReleasedRecyclerView(beanComingSoonMovielist, getContext());
            //线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //加载线性布局
            mReleasedRecyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            mReleasedRecyclerView.setAdapter(releasedRecyclerView);
            //点击预约
            releasedRecyclerView.setCallBackReleased(new ReleasedRecyclerView.CallBackReleased() {
                @Override
                public void onReleased(BeanComingSoonMovie.ResultBean bean) {
                    Toast.makeText(getContext(), "已预约" + bean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            //热门电影
        } else if (object instanceof BeanHotMovie) {
            BeanHotMovie beanHotMovie = (BeanHotMovie) object;
            String jsonHotMovie = new Gson().toJson(beanHotMovie);
            //存入表
            dataBeanMovie.setJsonHotMovie(jsonHotMovie);
            //转成list
            List<BeanHotMovie.ResultBean> beanHotMovielist = beanHotMovie.getResult();
            //设置适配器
            HotMovieRecyclerView hotMovieRecyclerView = new HotMovieRecyclerView(beanHotMovielist, getContext());
            //线性布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            //加载线性布局
            mPopularRecyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            mPopularRecyclerView.setAdapter(hotMovieRecyclerView);
            //点击购票
            hotMovieRecyclerView.setCallBackHotMovie(new HotMovieRecyclerView.CallBackHotMovie() {
                @Override
                public void onHotMovie(BeanHotMovie.ResultBean bean) {
                    Toast.makeText(getContext(), "已购票" + bean.getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        //存入数据库
        RxJavaUtil.getInstance().getDaoSession().getDataBeanMovieDao().insertOrReplace(dataBeanMovie);
    }

    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: " + failure);
        Toast.makeText(getContext(), "" + failure, Toast.LENGTH_SHORT).show();
    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
