package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adapter.IntroductionActor;
import com.bw.movie.adapter.IntroductionDirectorRecyclerView;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanFindMoviesDetail;

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
 * function：介绍
 */
public class FragmentIntroduction extends BaseFragment {
    @BindView(R.id.Introduction_)
    TextView mIntroduction;
    @BindView(R.id.Introduction_content)
    TextView mIntroductionContent;
    @BindView(R.id.Introduction_Castmember)
    TextView mIntroductionCastmember;
    @BindView(R.id.Introduction_director)
    TextView mIntroductionDirector;
    @BindView(R.id.Introduction_actor)
    TextView mIntroductionActor;
    Unbinder unbinder;
    private static final String TAG = "FragmentIntroduction";
    @BindView(R.id.Introduction_director_RecyclerView)
    RecyclerView mIntroductionDirectorRecyclerView;
    @BindView(R.id.Introduction_actor_RecyclerView)
    RecyclerView mIntroductionActorRecyclerView;

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_introduction_layout;
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

    }

   /* //接收EventBus    剧情
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getData(String summary) {
        if (!TextUtils.isEmpty(summary)) {
            mIntroductionContent.setText(summary);
        }
    }*/
    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanFindMoviesDetail.ResultBean bean) {
        //演员
        List<BeanFindMoviesDetail.ResultBean.MovieActorBean> movieActor = bean.getMovieActor();
        Log.i(TAG, "getDataBean: 演员"+movieActor.size());
        mIntroductionActor.setText("演员（"+movieActor.size()+"）");
        //设置适配器
        IntroductionActor introductionActor = new IntroductionActor(movieActor,getContext());
        //网格
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mIntroductionActorRecyclerView.setLayoutManager(gridLayoutManager);
        //设置适配器
        mIntroductionActorRecyclerView.setAdapter(introductionActor);
        //导演
        List<BeanFindMoviesDetail.ResultBean.MovieDirectorBean> movieDirector = bean.getMovieDirector();
        Log.i(TAG, "getDataBean: 导演"+movieDirector.size());
        mIntroductionDirector.setText("导演（"+movieDirector.size()+"）");
        //设置适配器
        IntroductionDirectorRecyclerView introductionDirectorRecyclerView = new IntroductionDirectorRecyclerView(movieDirector, getContext());
        //设置线性
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mIntroductionDirectorRecyclerView.setLayoutManager(linearLayoutManager);
        mIntroductionDirectorRecyclerView.setAdapter(introductionDirectorRecyclerView);
        //剧情
        String summary = bean.getSummary();
        mIntroductionContent.setText(summary);
    }

    /*//接收EventBus    演员
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataMovieActor(List<BeanFindMoviesDetail.ResultBean.MovieActorBean> movieActorBeanListList) {
        if (movieActorBeanListList.size() != 0) {
//            mIntroductionActor.setText("演员（" + movieActorBeanListList.size() + "）");
            //设置适配器
            IntroductionActor introductionActor = new IntroductionActor(movieActorBeanListList,getContext());
            //设置线性
            *//*GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            mIntroductionActorRecyclerView.setLayoutManager(gridLayoutManager);*//*

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
            mIntroductionActorRecyclerView.setLayoutManager(linearLayoutManager);
            //设置适配器
            mIntroductionActorRecyclerView.setAdapter(introductionActor);
        }
    }*/

//    //接收EventBus    导演
//    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
//    public void getDataMovieDirector(List<BeanFindMoviesDetail.ResultBean.MovieDirectorBean> movieDirectorBeanList) {
//        if (movieDirectorBeanList.size() != 0) {
//            /*//导演人数
//            mIntroductionDirector.setText("导演（" + movieDirectorBeanList.size() + "）");
//            //设置适配器
//            IntroductionDirectorRecyclerView introductionDirectorRecyclerView = new IntroductionDirectorRecyclerView(movieDirectorBeanList, getContext());
//            //设置线性
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//            linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
//            mIntroductionDirectorRecyclerView.setLayoutManager(linearLayoutManager);
//            mIntroductionDirectorRecyclerView.setAdapter(introductionDirectorRecyclerView);*/
//        }
//    }

    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
