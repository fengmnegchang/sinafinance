package com.open.sina.finance.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshPinnedSectionListView;
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

abstract class CommonRefreshSectionListViewFragment extends CommonRefreshFragment<PullToRefreshPinnedSectionListView, ListView,CommonRefreshSectionListViewFragment>
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.common_refresh_section_listview, container, false);
            setTextView((TextView) view.findViewById(R.id.txt_list_toast));
            setRefreshView((PullToRefreshPinnedSectionListView) view.findViewById(R.id.common_refresh_section_list));
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

    public void setAdapter(BaseAdapter adapter) {
        if (getActivity() != null && getRefreshView() != null) {
            getRefreshView().setOnItemClickListener(this);
            getRefreshView().setOnItemLongClickListener(this);
            getRefreshView().setAdapter(adapter);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    }

}
