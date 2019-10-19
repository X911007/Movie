package com.bw.movie.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.HotMovieRecyclerView;
import com.bw.movie.adapter.VideoSearchPagePopularmovieXRecyclerView;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanHotMovie;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/10/14 15:15:00
 * function：影片搜索页——热门电影
 */
public class FragmentVideoSearchPagePopularmovie extends BaseFragment implements Contract.HomeView {

    @BindView(R.id.VideoSearchPagePopularmovie_XRecyclerView)
    XRecyclerView mVideoSearchPagePopularmovieXRecyclerView;
    Unbinder unbinder;
    private Presenter presenter;
    private Map<String, Integer> qqmap = new HashMap<>();
    private static final String TAG = "FragmentVideoSearchPage";
    private int page = 1;
    private int count = 5;
    private List<BeanHotMovie.ResultBean> list_s = new ArrayList<>();

    //加载布局
    @Override
    public int initLayoutInflater() {
        return R.layout.videosearchpage_popularmovie_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
        //联网
        getNet(page, count);
        //加载更多
        mVideoSearchPagePopularmovieXRecyclerView.setLoadingMoreEnabled(true);
        mVideoSearchPagePopularmovieXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //刷新
            @Override
            public void onRefresh() {
                list_s.clear();
                page = 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPagePopularmovieXRecyclerView.refreshComplete();
                    }
                }, 2000);
            }
            //加载
            @Override
            public void onLoadMore() {
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPagePopularmovieXRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }
    //联网请求
    private void getNet(int page, int count) {
        //热门电影
        qqmap.put(Api.URL_PAGE, page);
        qqmap.put(Api.URL_COUNT, count);
        presenter.getHotMovie(qqmap);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object);
        BeanHotMovie beanHotMovie = (BeanHotMovie) object;
        List<BeanHotMovie.ResultBean> beanHotMovielist = beanHotMovie.getResult();
        //存入大集合
        list_s.addAll(beanHotMovielist);
        Log.i(TAG, "onSuccess: " + beanHotMovielist.get(0).getName());
        //设置适配器
        //HotMovieRecyclerView hotMovieRecyclerView = new HotMovieRecyclerView(beanHotMovielist, getContext());
        VideoSearchPagePopularmovieXRecyclerView videoSearchPagePopularmovieXRecyclerView = new VideoSearchPagePopularmovieXRecyclerView(list_s, getContext());
        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //加载线性布局
        mVideoSearchPagePopularmovieXRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mVideoSearchPagePopularmovieXRecyclerView.setAdapter(videoSearchPagePopularmovieXRecyclerView);
        //点击购票
        videoSearchPagePopularmovieXRecyclerView.setCallBackHotMoviemovie(new VideoSearchPagePopularmovieXRecyclerView.CallBackHotMoviemovie() {
            @Override
            public void onHotMovie(BeanHotMovie.ResultBean bean) {
                Toast.makeText(getContext(), "已购票" + bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        mVideoSearchPagePopularmovieXRecyclerView.setLoadingMoreEnabled(true);
        mVideoSearchPagePopularmovieXRecyclerView.setPullRefreshEnabled(true);
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
