package com.open.sina.finance.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.open.sina.finance.R;
import com.open.sina.finance.adapter.GuideViewPagerAdapter;
import com.open.sina.finance.base.activity.CommonTitleBarActivity;
import com.open.sina.finance.bean.MainTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/26.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class GuideActivity extends CommonTitleBarActivity {
    private ViewPager viewpager;
    private GuideViewPagerAdapter mGuideViewPagerAdapter;
    public List<MainTabBean> list = new ArrayList<MainTabBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivity(this);
        setImplementsDrawerBack(false);
        setExtendsCommonTitleBarActivity(false);
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_guide);
        setExtendsCommonTitleBarActivity(true);
    }

    @Override
    public void initControl() {
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void initValue() {
        super.initValue();
        list.add(new MainTabBean(getResources().getIdentifier("bg_guide0", "drawable", getPackageName()),""));
        list.add(new MainTabBean(getResources().getIdentifier("bg_guide1", "drawable", getPackageName()),""));
        list.add(new MainTabBean(getResources().getIdentifier("bg_guide2", "drawable", getPackageName()),""));
        list.add(new MainTabBean(getResources().getIdentifier("bg_guide3", "drawable", getPackageName()),""));
        mGuideViewPagerAdapter = new GuideViewPagerAdapter(this,list);
        viewpager.setAdapter(mGuideViewPagerAdapter);
        mGuideViewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindEvent() {
        super.bindEvent();
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
