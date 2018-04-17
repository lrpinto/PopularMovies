package com.example.android.popularmovies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.popularmovies.adapter.ViewPagerAdapter;
import com.example.android.popularmovies.data.FavouriteMoviesContract;
import com.example.android.popularmovies.data.FavouriteMoviesDbHelper;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        setViewPager();

    }

    private void setViewPager() {

        ViewPager mViewPager = findViewById(R.id.pager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout mTabLayout = findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}