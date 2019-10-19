package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Xuexiandong
 * data: 2019/9/29 21:21:18
 * function：电影详情页ViewPager适配器
 */
public class MoviedetailspageViewPagerAdapter extends FragmentPagerAdapter {
    private List<String >list_b;
    private List<Fragment>list_f;

    public MoviedetailspageViewPagerAdapter(FragmentManager fm, List<String> list_b, List<Fragment> list_f) {
        super(fm);
        this.list_b = list_b;
        this.list_f = list_f;
    }

    @Override
    public Fragment getItem(int i) {
        return list_f.get(i);
    }

    @Override
    public int getCount() {
        return list_f.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list_b.get(position);
    }
}
