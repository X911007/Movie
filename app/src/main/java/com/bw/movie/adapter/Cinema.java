package com.bw.movie.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * author: Xuexiandong
 * data: 2019/10/18 19:19:44
 * function：影院  适配器
 */
public class Cinema extends FragmentPagerAdapter {
    private List<Fragment> list_f = new ArrayList<>();
    private List<String> list_b = new ArrayList<>();

    public Cinema(FragmentManager fm, List<Fragment> list_f, List<String> list_b) {
        super(fm);
        this.list_f = list_f;
        this.list_b = list_b;
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
