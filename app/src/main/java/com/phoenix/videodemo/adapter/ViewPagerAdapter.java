package com.phoenix.videodemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by flashing on 2017/3/20.
 */

public class ViewPagerAdapter extends PagerAdapter {
    public List<View> datas;

    public ViewPagerAdapter(List<View> datas){
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(datas.get(position), 0);
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }
}