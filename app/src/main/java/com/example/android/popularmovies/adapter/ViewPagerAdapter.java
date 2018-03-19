package com.example.android.popularmovies.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android.popularmovies.fragment.PopularFragment;
import com.example.android.popularmovies.fragment.TopRatedFragment;


/**
 * Created by Luisa on 03/03/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TopRatedFragment.newInstance();
            case 1:
                return PopularFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TopRatedFragment.TITLE;

            case 1:
                return PopularFragment.TITLE;

        }
        return super.getPageTitle(position);
    }
}