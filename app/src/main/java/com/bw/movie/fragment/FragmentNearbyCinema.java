package com.bw.movie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activity.SelectionActivity;
import com.bw.movie.adapter.FindNearbyCinemasResult;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanFindNearbyCinemas;
import com.bw.movie.bean.BeanGeographicLocation;
import com.bw.movie.bean.BeanReleaseMovie;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.Api;
import com.bw.movie.util.RxJavaUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/10/18 19:19:09
 * function：附近影院
 */
public class FragmentNearbyCinema extends BaseFragment implements Contract.HomeView {
    Unbinder unbinder;
    @BindView(R.id.nearby_cinema_RecyclerView)
    XRecyclerView mNearbyCinemaRecyclerView;
    private Presenter presenter;
    private static final String TAG = "FragmentNearbyCinema";
    private Map<String, String> hmap = new HashMap<>();
    private Map<String, Object> qmap = new HashMap<>();
    private String latitude;
    private String longitude;
    private FindNearbyCinemasResult findNearbyCinemasResult;

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_nearby_cinema_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        //注册
        EventBus.getDefault().register(this);
        //取出经纬度
        //纬度
        //latitude = RxJavaUtil.getInstance().getBeanGeographicLocation().getLatitude();
        Log.i(TAG, "aaaaaonCreateView: " + latitude);
        //经度
        //longitude = RxJavaUtil.getInstance().getBeanGeographicLocation().getLongitude();
        Log.i(TAG, "bbbbbbbonCreateView: " + longitude);
        return rootView;
    }

    //接收EventBus    bean
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getDataBean(BeanGeographicLocation bean) {
        if (bean != null) {
            //纬度
             latitude = bean.getLatitude();
            //经度
             longitude = bean.getLongitude();

        }
    }

    //数据
    @Override
    public void initData() {
        presenter = new Presenter(this);
        //附近影院
        hmap.put(Api.URL_USERID, Api.URL_USERID_S);
        hmap.put(Api.URL_SESSIONID, Api.URL_SESSIONID_S);
        qmap.put(Api.URL_LATITUDE, Api.URL_LATITUDE_S);
        qmap.put(Api.URL_LONGITUDE, Api.URL_LONGITUDE_S);
        presenter.getFindNearbyCinemas(hmap, qmap);
    }

    //成功
    @Override
    public void onSuccess(Object object) {
        Log.i(TAG, "onSuccess: " + object);
        BeanFindNearbyCinemas beanFindNearbyCinemas = (BeanFindNearbyCinemas) object;
        List<BeanFindNearbyCinemas.ResultBean> beanFindNearbyCinemasResult = beanFindNearbyCinemas.getResult();
        Log.i(TAG, "zzzonSuccess: " + beanFindNearbyCinemasResult.size());
        Log.i(TAG, "xxxonSuccess: " + beanFindNearbyCinemasResult.get(0).getName());
        //设置适配器
        findNearbyCinemasResult = new FindNearbyCinemasResult(beanFindNearbyCinemasResult, getContext());
        mNearbyCinemaRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置适配器
        mNearbyCinemaRecyclerView.setAdapter(findNearbyCinemasResult);
        //点击附近影院
        findNearbyCinemasResult.setCallBackFindNearbyCinemasResult(new FindNearbyCinemasResult.CallBackFindNearbyCinemasResult() {
            @Override
            public void onNearbyCinemas(int str) {
                //获取影院id
                //Toast.makeText(getContext(), "影院ID"+str, Toast.LENGTH_SHORT).show();
                //已选择的电影
                RxJavaUtil.getInstance().getMovie().setCinemaId(str);
                //判空
                int movieId = RxJavaUtil.getInstance().getMovie().getMovieId();
                int cinemaId = RxJavaUtil.getInstance().getMovie().getCinemaId();
                if (movieId!=0&&cinemaId!=0) {
                    //Toast.makeText(getContext(), "去选座", Toast.LENGTH_SHORT).show();
                    getContext().startActivity(new Intent(getContext(), SelectionActivity.class));
                }
            }
        });
    }
//ApowerMirror
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
