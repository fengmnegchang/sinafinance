package com.open.sina.finance.base.activity;

import android.os.Bundle;
import android.view.View;
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

public abstract class CommonRefreshPinnedSectionListActivity extends CommonRefreshActivity<PullToRefreshPinnedSectionListView, ListView> implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener{

    private boolean isExtendsRefreshPinnedSectionListView = true;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isExtendsRefreshPinnedSectionListView) {
            addContentView(R.layout.common_refresh_section_listview);
        }
    }

    @Override
    public void initControl() {
        if (isExtendsRefreshPinnedSectionListView) {
            setTextView((TextView) findViewById(R.id.txt_list_toast));
            setRefreshView((PullToRefreshPinnedSectionListView) findViewById(R.id.common_refresh_section_list));

            setLoadingLayoutTextColor(R.color.status_bar_color);
        }
    }

    /**
     * 设置listview 适配器
     */
    public void setAdapter(BaseAdapter adapter) {
        if (getRefreshView() != null) {
            getRefreshView().setOnItemClickListener(this);
            getRefreshView().setOnItemLongClickListener(this);
            getRefreshView().setAdapter(adapter);
        }
    }

    /**
     * 是否继承本类布局
     * @param isExtendsRefreshPinnedSectionListView
     */
    public void setIsExtendsRefreshPinnedSectionListView(boolean isExtendsRefreshPinnedSectionListView) {
        this.isExtendsRefreshPinnedSectionListView = isExtendsRefreshPinnedSectionListView;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        return false;
    }
}
