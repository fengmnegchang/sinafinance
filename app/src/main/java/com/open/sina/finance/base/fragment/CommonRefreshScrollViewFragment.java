package com.open.sina.finance.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

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

public abstract class CommonRefreshScrollViewFragment extends CommonRefreshFragment<PullToRefreshScrollView, ScrollView,CommonRefreshScrollViewFragment>{
    public View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.common_refresh_scrollview, container, false);
            setRefreshView((PullToRefreshScrollView) view.findViewById(R.id.refresh_scrollview));
            setTextView((TextView) view.findViewById(R.id.txt_list_toast));
            setLoadingLayoutTextColor(R.color.status_bar_color);
        }
        if (view != null && view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }
}
