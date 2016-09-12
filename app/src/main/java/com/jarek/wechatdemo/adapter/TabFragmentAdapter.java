package com.jarek.wechatdemo.adapter;

import android.support.v4.app.*;
import android.view.ViewGroup;

/**
 * <p>主页fragment 适配器</p>
 * Created by Jarek(王健) on 2016-3-10
 */
public class TabFragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Fragment[] fragments;

    public TabFragmentAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position % fragments.length];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
