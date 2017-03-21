package com.phoenix.videodemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.phoenix.videodemo.fragment.HomeFragment;
import com.phoenix.videodemo.homefragment.HotFragment;
import com.phoenix.videodemo.homefragment.MovieFragment;
import com.phoenix.videodemo.homefragment.VedioFragment;

/**
 * Created by C罗 on 2017/3/21.
 */

public class MyHomeFragmentAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 3;
    private VedioFragment myFragment1 = null;
    private HotFragment myFragment2 = null;
    private MovieFragment myFragment3 = null;

    public MyHomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new VedioFragment();
        myFragment2 = new HotFragment();
        myFragment3 = new MovieFragment();

    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case HomeFragment.PAGE_ONE:
                fragment = myFragment1;
                break;
            case HomeFragment.PAGE_TWO:
                fragment = myFragment2;
                break;
            case HomeFragment.PAGE_THREE:
                fragment = myFragment3;
                break;

        }
        return fragment;
    }

}
