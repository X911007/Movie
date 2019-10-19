package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.adapter.ViewPagerAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.fragment.FragmentCinema;
import com.bw.movie.fragment.FragmentMine;
import com.bw.movie.fragment.FragmentMovie;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 展示
 */
public class ShowActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.mevie_a)
    ImageView mMevieA;
    @BindView(R.id.mevie_b)
    LinearLayout mMevieB;
    @BindView(R.id.left)
    LinearLayout mLeft;
    @BindView(R.id.cinema_a)
    ImageView mCinemaA;
    @BindView(R.id.cinema_b)
    LinearLayout mCinemaB;
    @BindView(R.id.middle)
    LinearLayout mMiddle;
    @BindView(R.id.my_a)
    ImageView mMyA;
    @BindView(R.id.my_b)
    LinearLayout mMyB;
    @BindView(R.id.right)
    LinearLayout mRight;
    private Unbinder bind;
    private List<Fragment> list_f = new ArrayList<>();
    private static final String TAG = "ShowActivity";

    //布局
    @Override
    public int initLayoutResID() {
        return R.layout.activity_show;
    }

    //注册
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
        //电影
        list_f.add(new FragmentMovie());
        //影院
        list_f.add(new FragmentCinema());
        //我的
        list_f.add(new FragmentMine());
        //设置适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list_f);
        mVp.setAdapter(viewPagerAdapter);

        //默认加载
//        mVp.setCurrentItem(0);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mMevieA.setVisibility(View.GONE);
                        mMevieB.setVisibility(View.VISIBLE);
                        mCinemaA.setVisibility(View.VISIBLE);
                        mCinemaB.setVisibility(View.GONE);
                        mMyA.setVisibility(View.VISIBLE);
                        mMyB.setVisibility(View.GONE);
                        break;
                    case 1:
                        mMevieA.setVisibility(View.VISIBLE);
                        mMevieB.setVisibility(View.GONE);
                        mCinemaA.setVisibility(View.GONE);
                        mCinemaB.setVisibility(View.VISIBLE);
                        mMyA.setVisibility(View.VISIBLE);
                        mMyB.setVisibility(View.GONE);
                        break;
                    case 2:
                        mMevieA.setVisibility(View.VISIBLE);
                        mMevieB.setVisibility(View.GONE);
                        mCinemaA.setVisibility(View.VISIBLE);
                        mCinemaB.setVisibility(View.GONE);
                        mMyA.setVisibility(View.GONE);
                        mMyB.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //预加载
        mVp.setOffscreenPageLimit(3);
        Intent intent = getIntent();
        int classification = intent.getIntExtra("推荐",0);
        if (classification==0){
            mVp.setCurrentItem(0);
        }else if (classification==1){
            mVp.setCurrentItem(1);
        }else if (classification==2){
            mVp.setCurrentItem(2);
        }
    }
    //点击
    @OnClick({R.id.left, R.id.middle, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                mMevieA.setVisibility(View.GONE);
                mMevieB.setVisibility(View.VISIBLE);
                mCinemaA.setVisibility(View.VISIBLE);
                mCinemaB.setVisibility(View.GONE);
                mMyA.setVisibility(View.VISIBLE);
                mMyB.setVisibility(View.GONE);
                mVp.setCurrentItem(0);
                break;
            case R.id.middle:
                mMevieA.setVisibility(View.VISIBLE);
                mMevieB.setVisibility(View.GONE);
                mCinemaA.setVisibility(View.GONE);
                mCinemaB.setVisibility(View.VISIBLE);
                mMyA.setVisibility(View.VISIBLE);
                mMyB.setVisibility(View.GONE);
                mVp.setCurrentItem(1);
                break;
            case R.id.right:
                mMevieA.setVisibility(View.VISIBLE);
                mMevieB.setVisibility(View.GONE);
                mCinemaA.setVisibility(View.VISIBLE);
                mCinemaB.setVisibility(View.GONE);
                mMyA.setVisibility(View.GONE);
                mMyB.setVisibility(View.VISIBLE);
                mVp.setCurrentItem(2);
                break;
        }

    }

    //对象
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    //数据
    @Override
    public void initData() {


    }

    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
