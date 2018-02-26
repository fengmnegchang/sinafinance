package com.open.sina.finance.base.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Administrator
 * @createTime: 2018/1/5
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class ViewHolder {

    public View itemView;
    private SparseArray<View> mHolderViews;

    public ViewHolder(View view) {
        itemView = view;
        view.setTag(this);
        mHolderViews = new SparseArray<>();
    }

    public void hold(int... resIds) {
        for (int id : resIds) {
            mHolderViews.put(id, itemView.findViewById(id));
        }
    }

    public <V> V get(int id) {
        return (V) mHolderViews.get(id);
    }
}
