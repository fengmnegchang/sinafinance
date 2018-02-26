package com.open.sina.finance.base.adapter;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/15.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;
    private List<String> titleList;
    private FragmentManager fm;

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragment, List<String> titleList) {
        super(fm);
        this.listFragment = listFragment;
        this.fm = fm;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fm.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = listFragment.get(position);
        fm.beginTransaction().hide(fragment).commit();
    }



}
