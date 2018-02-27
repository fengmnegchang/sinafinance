package com.open.sina.finance.activity.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.open.sina.finance.R;
import com.open.sina.finance.base.activity.CommonTitleBarActivity;
import com.open.sina.finance.base.adapter.CommonFragmentPagerAdapter;
import com.open.sina.finance.bean.IndicatorBean;
import com.open.sina.finance.fragment.news.NewsFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/27.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class NewsTabActivity extends CommonTitleBarActivity {
    public ArrayList<IndicatorBean> list = new ArrayList<IndicatorBean>();
    public ViewPager viewpager;
    public TabPageIndicator indicator;
    public List<String> titleList = new ArrayList<String>();
    public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
    public CommonFragmentPagerAdapter mRankPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setActivity(this);
        setImplementsDrawerBack(false);
        setExtendsCommonTitleBarActivity(false);
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_news_tab,true);
    }


    @Override
    public void initControl() {
        super.initControl();
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
    }

    @Override
    public void initValue() {
        super.initValue();
        list.clear();
        list.add(new IndicatorBean("头条"));

        mRankPagerAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), listRankFragment, titleList);
        viewpager.setAdapter(mRankPagerAdapter);
        indicator.setViewPager(viewpager);


        titleList.clear();
        listRankFragment.clear();
        for (int i = 0; i < list.size(); i++) {
            Fragment fragment;
            titleList.add(list.get(i).inName);
            if (i == 0) {
                fragment = NewsFragment.newInstance(false);
            } else {
                fragment = NewsFragment.newInstance(false);
            }
            listRankFragment.add(fragment);
        }
        mRankPagerAdapter.notifyDataSetChanged();
        indicator.notifyDataSetChanged();
    }


    @Override
    public void bindEvent() {
        super.bindEvent();
    }
}
