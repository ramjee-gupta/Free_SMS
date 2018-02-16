package com.ram.freesms;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewpager;
    private TabLayout mTablayout;
    private SectionPagerAdapter mSectionPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Free SMS");


        mViewpager = (ViewPager) findViewById(R.id.main_viewpager);
        mSectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mSectionPagerAdapter);

        mTablayout = findViewById(R.id.mainPage_tabs);
        mTablayout.setupWithViewPager(mViewpager);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
