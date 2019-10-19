package com.bw.movie.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.bw.movie.R;
import com.bw.movie.adapter.Cinema;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.BeanFindRecommendCinemas;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;
import com.bw.movie.util.AMapLocationUtil;
import com.bw.movie.util.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:00
 * function：影院
 */
public class FragmentCinema extends BaseFragment {

    private static final String TAG = "FragmentCinema";
    @BindView(R.id.tab_cinema)
    TabLayout mTabCinema;
    @BindView(R.id.vp_cinema)
    ViewPager mVpCinema;
    Unbinder unbinder;
    @BindView(R.id.Cinema_Positioning)
    ImageView mCinemaPositioning;
    @BindView(R.id.Cinema_text)
    TextView mCinemaText;
    @BindView(R.id.Cinema_search)
    ImageView mCinemaSearch;
    private List<Fragment> list_f = new ArrayList<>();
    private List<String> list_b = new ArrayList<>();

    //布局
    @Override
    public int initLayoutInflater() {
        return R.layout.fragment_cinema_layout;
    }

    //注册
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        //推荐影院
        list_f.add(new FragmentRecommendCinema());
        //附近影院
        list_f.add(new FragmentNearbyCinema());
        //区
        list_f.add(new FragmentAreaCinema());

        list_b.add("推荐影院");
        list_b.add("附近影院");
        list_b.add("区");
        return rootView;
    }
    //点击
    @OnClick({R.id.Cinema_Positioning, R.id.Cinema_text, R.id.Cinema_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Cinema_Positioning://定位
                Toast.makeText(getContext(), "已定位", Toast.LENGTH_SHORT).show();
                //定位方法
                AMapLocationUtil.getLoaction(new AMapLocationUtil.AMapInterface() {
                    @Override
                    public void getAMapLocation(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            if (aMapLocation.getErrorCode() == 0) {
                                //可在其中解析amapLocation获取相应内容。
                                String city = aMapLocation.getCity();
                                String district = aMapLocation.getDistrict();
                                String street = aMapLocation.getStreet();
                                String streetNum = aMapLocation.getStreetNum();
                                Log.i(TAG, "111111111getAMapLocation: " + aMapLocation.getCity());
                                Toast.makeText(getContext(), "" + city + district + street + streetNum, Toast.LENGTH_SHORT).show();
                                mCinemaText.setText(city + district + street + streetNum);
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
            case R.id.Cinema_search://搜索
                Toast.makeText(getContext(), "已搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    //数据
    @Override
    public void initData() {
        //设置适配器
        Cinema cinema = new Cinema(getChildFragmentManager(), list_f, list_b);
        mVpCinema.setAdapter(cinema);
        mTabCinema.setupWithViewPager(mVpCinema);
        //预加载
        mVpCinema.setOffscreenPageLimit(3);

    }
    //释放
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
