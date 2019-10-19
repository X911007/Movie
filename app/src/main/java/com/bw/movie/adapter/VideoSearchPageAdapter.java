package com.bw.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: Xuexiandong
 * data: 2019/10/14 15:15:10
 * function：影片搜索页适配器
 */
public class VideoSearchPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list_f;

    public VideoSearchPageAdapter(FragmentManager fm, List<Fragment> list_f) {
        super(fm);
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
}
