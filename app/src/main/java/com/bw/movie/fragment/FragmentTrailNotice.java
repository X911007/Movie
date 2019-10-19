package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.IntroductionDirectorRecyclerView;
import com.bw.movie.adapter.TrailNotice;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBaseView;
import com.bw.movie.bean.BeanFindMoviesDetail;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：预告
 */
public class FragmentTrailNotice extends BaseFragment implements IBaseView {
    @BindView(R.id.TrailNotice_RecyclerView)
    RecyclerView mTrailNoticeRecyclerView;
    Unbinder unbinder;
    private Presenter presenter;
    private static final String TAG = "FragmentTrailNotice";

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_trailnotice_layout;
    }
    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        //注册
        EventBus.getDefault().register(this);
        return rootView;
    }
    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);

    }

    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanFindMoviesDetail.ResultBean bean) {
        List<BeanFindMoviesDetail.ResultBean.ShortFilmListBean> shortFilmList = bean.getShortFilmList();
        String videoUrl = shortFilmList.get(0).getVideoUrl();
        Log.i(TAG, "getData: "+videoUrl);
        //设置适配器
        TrailNotice trailNotice = new TrailNotice(R.layout.trailnotice_recyclerview_item_layout,shortFilmList);
        //设置管理器
        mTrailNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTrailNoticeRecyclerView.setAdapter(trailNotice);
    }
    /*//接收EventBus
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getData(List<BeanFindMoviesDetail.ResultBean.ShortFilmListBean> shortFilmList) {
        if (shortFilmList.size()!=0){
            String videoUrl = shortFilmList.get(0).getVideoUrl();
            Log.i(TAG, "getData: "+videoUrl);
            //设置适配器
            TrailNotice trailNotice = new TrailNotice(R.layout.trailnotice_recyclerview_item_layout,shortFilmList);
            //设置管理器
            mTrailNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mTrailNoticeRecyclerView.setAdapter(trailNotice);
        }
    }*/

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: "+object.toString());
    }
    //失败
    @Override
    public void onFailure(String failure) {
        Log.i(TAG, "onFailure: "+failure);
        Toast.makeText(getContext(), ""+failure, Toast.LENGTH_SHORT).show();
    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
