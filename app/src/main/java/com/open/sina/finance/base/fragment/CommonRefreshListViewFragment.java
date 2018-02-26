package com.open.sina.finance.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
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

public class CommonRefreshListViewFragment extends CommonRefreshFragment<PullToRefreshListView, ListView,CommonRefreshListViewFragment> implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.common_refresh_listview, container, false);
        setRefreshView((PullToRefreshListView) view.findViewById(R.id.refresh_lst));
        setTextView((TextView)  view.findViewById(R.id.txt_list_toast));
        setLoadingLayoutTextColor(R.color.status_bar_color);
        if (view != null && view.getParent() != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return view;
    }

//    @Override
//    public void findView() {
//        super.findView();
//        setRefreshView((PullToRefreshListView) view.findViewById(R.id.refresh_lst));
//        if (view != null && view.getParent() != null) {
//            ViewGroup parent = (ViewGroup) view.getParent();
//            if (parent != null) {
//                parent.removeView(view);
//            }
//        }
//    }

    /**
     * 设置listview 适配器
     * @param adapter
     * @description:
     */
    public void setAdapter(BaseAdapter adapter) {
        if (getActivity() != null && getRefreshView() != null) {
            getRefreshView().setAdapter(adapter);
            getRefreshView().setOnItemLongClickListener(this);
            getRefreshView().setOnItemClickListener(this);
            getPullView().setOnRefreshListener(this);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }

}
