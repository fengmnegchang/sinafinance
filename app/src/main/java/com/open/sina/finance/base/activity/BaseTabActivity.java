package com.open.sina.finance.base.activity;

import android.app.TabActivity;
import android.os.Bundle;

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

public class BaseTabActivity extends TabActivity {
    public final   String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackManager.getInstance().pushActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().popActivity(this);
    }


    protected void init() {
        try {
            findView();
            initValue();
            bindEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 取出控件
     */
    protected void findView() {

    }

    /**
     * 实例化控件
     */
    protected void initValue() {

    }

    /**
     * 绑定事件
     */
    protected void bindEvent() {

    }
}
