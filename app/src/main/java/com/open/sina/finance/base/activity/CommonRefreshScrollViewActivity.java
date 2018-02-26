package com.open.sina.finance.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.open.sina.finance.R;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/16.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public abstract class CommonRefreshScrollViewActivity extends CommonRefreshActivity<PullToRefreshScrollView, ScrollView>{
    public static String TAG = CommonRefreshScrollViewActivity.class.getSimpleName();
    /* 是否继承此控件 */
    private boolean isExtendsRefreshScrollView = true;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isExtendsRefreshScrollView) {
            addContentView(R.layout.common_refresh_scrollview);
        }
    }

    @Override
    public void initControl() {
        if (isExtendsRefreshScrollView) {
            setRefreshView((PullToRefreshScrollView) findViewById(R.id.refresh_scrollview));
            setTextView((TextView) findViewById(R.id.txt_list_toast));
            setRefreshMode(PullToRefreshBase.Mode.PULL_FROM_START);

            setLoadingLayoutTextColor(R.color.status_bar_color);
        }
    }

    @Override
    public void bindEvent() {
    }

    @Override
    public void initValue() {
    }

    /**
     * 添加下拉刷新，上拉加载更多页
     */
    protected void addScrollView(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            View v = inflater.inflate(layoutResId, null);
            if (getRefreshView() != null) {
                getRefreshView().addView(v);
                initScrollControl();
                initSrcollValue();
                initScrollBindEvent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化滚动页控件 findViewById(R.id.xxxx)
     */
    protected abstract void initScrollControl();

    /**
     * 设置监听事件
     */
    protected abstract void initScrollBindEvent();

    /**
     * 设置初始值
     */
    protected abstract void initSrcollValue();

    /**
     * 设置是否继承刷新布局
     * @param isExtendsRefreshScrollView
     */
    public void setIsExtendsRefreshScrollView(boolean isExtendsRefreshScrollView) {
        this.isExtendsRefreshScrollView = isExtendsRefreshScrollView;
    }


}
