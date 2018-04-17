package com.example.android.popularmovies.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.android.popularmovies.fragment.FavouritesFragment;
import com.example.android.popularmovies.fragment.PopularFragment;
import com.example.android.popularmovies.fragment.TopRatedFragment;


/**
 * ViewPagerAdapter.java
 * Created by Luisa on 03/03/2018.
 * <p>
 * View pager to manage changes between tabs.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private static TopRatedFragment topRatedFragment;
    private static PopularFragment popularFragment;
    private static FavouritesFragment favouritesFragment;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static void reloadFavouriteMovies(Context context) {

        if (favouritesFragment != null) {
            favouritesFragment.loadMovies(context);
        }
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TopRatedFragment.newInstance();
            case 1:
                return PopularFragment.newInstance();
            case 2:
                return FavouritesFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return TopRatedFragment.TITLE;
            case 1:
                return PopularFragment.TITLE;
            case 2:
                return FavouritesFragment.TITLE;

        }
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

        switch (position) {
            case 0:
                topRatedFragment = (TopRatedFragment) fragment;
                break;
            case 1:
                popularFragment = (PopularFragment) fragment;
                break;
            case 2:
                favouritesFragment = (FavouritesFragment) fragment;
                break;
        }
        return fragment;
    }
}