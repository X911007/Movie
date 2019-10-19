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
import com.bw.movie.adapter.VideoSearchPageComingSoon;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanComingSoonMovie;
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
 * function：影片搜索页——即将上映
 */
public class FragmentVideoSearchPageComingSoon extends BaseFragment implements Contract.HomeView {

    Unbinder unbinder;
    @BindView(R.id.VideoSearchPageComingSoon_XRecyclerView)
    XRecyclerView mVideoSearchPageComingSoonXRecyclerView;
    private Presenter presenter;
    private Map<String, String> hmap = new HashMap<>();
    private Map<String, Integer> qmap = new HashMap<>();
    private static final String TAG = "FragmentVideoSearchPage";
    private int page = 1;
    private int count = 5;
    private List<BeanComingSoonMovie.ResultBean> list_s=new ArrayList<>();

    //加载布局
    @Override
    public int initLayoutInflater() {
        return R.layout.videosearchpage_comingsoon_layout;
    }

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
        getNet(page,count);
        //刷新加载
        mVideoSearchPageComingSoonXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list_s.clear();
                page=1;
                getNet(page,count);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPageComingSoonXRecyclerView.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                page++;
                getNet(page,count);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNet(page, count);
                        mVideoSearchPageComingSoonXRecyclerView.loadMoreComplete();
                    }
                }, 2000);
            }
        });
    }

    //联网
    private void getNet(int page,int count) {
        //即将上映
        hmap.put(Api.URL_USERID, Api.URL_USERID_S);
        hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);

        qmap.put(Api.URL_PAGE, page);
        qmap.put(Api.URL_COUNT, count);
        presenter.getComingSoonMovie(hmap, qmap);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object);
        BeanComingSoonMovie beanComingSoonMovie = (BeanComingSoonMovie) object;
        List<BeanComingSoonMovie.ResultBean> beanComingSoonMovielist = beanComingSoonMovie.getResult();
        Log.i(TAG, "111111111111onSuccess: " + beanComingSoonMovielist.get(0).getName());
        list_s.addAll(beanComingSoonMovielist);
        //设置适配器
        VideoSearchPageComingSoon videoSearchPageComingSoon = new VideoSearchPageComingSoon(list_s, getContext());
        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //加载线性布局
        mVideoSearchPageComingSoonXRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mVideoSearchPageComingSoonXRecyclerView.setAdapter(videoSearchPageComingSoon);
        //点击预约
        videoSearchPageComingSoon.setCallBackTicketsSoon(new VideoSearchPageComingSoon.CallBackTicketsSoon() {
            @Override
            public void onTickets(BeanComingSoonMovie.ResultBean bean) {
                Toast.makeText(getContext(), "已预约" + bean.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置加载
        mVideoSearchPageComingSoonXRecyclerView.setLoadingMoreEnabled(true);
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
