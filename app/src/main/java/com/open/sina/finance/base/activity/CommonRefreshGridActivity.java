package com.open.sina.finance.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;
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

public abstract class CommonRefreshGridActivity extends CommonRefreshActivity<PullToRefreshGridView, GridView> implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    protected boolean isExtendsRefreshGridView = true;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isExtendsRefreshGridView) {
            addContentView(R.layout.common_refresh_gridview);
        }
    }

    @Override
    public void initControl() {
        if (isExtendsRefreshGridView) {
            setRefreshView((PullToRefreshGridView) findViewById(R.id.pull_refresh_gridview));
            setTextView((TextView) findViewById(R.id.txt_list_toast));

            setLoadingLayoutTextColor(R.color.status_bar_color);
        }
    }

    /**
     * 设置是否继承refreshListView;一定要super.onCreate(bundle)之前 默认继承
     * @param isExtendsRefreshGridView 默认true
     */
    protected void setExtendsRefreshGridView(boolean isExtendsRefreshGridView) {
        this.isExtendsRefreshGridView = isExtendsRefreshGridView;
    }

    /**
     * 设置listview 适配器
     */
    public void setAdapter(BaseAdapter adapter) {
        if (getRefreshView() != null) {
            getRefreshView().setAdapter(adapter);
            getRefreshView().setOnItemLongClickListener(this);
            getRefreshView().setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        return false;
    }

}
