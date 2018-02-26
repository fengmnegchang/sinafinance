package com.open.sina.finance.refresh;

import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/10.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public interface OnHandleRefreshListener<T extends PullToRefreshBase<V>, V extends View> {


    /***
     * 设置刷新模式
     * @param mode
     */
    void setRefreshMode(PullToRefreshBase.Mode mode);


    /***
     * 得到被刷新的控件
     * @return
     */
    V getRefreshView();


    /**
     * 得到刷新的控件 包含被刷新的控件
     */
    T getPullView();

    /****
     * 设置正在刷新
     */
    void setRefreshing();


    /***
     * 停止刷新
     */
    void onStopRefresh();


    /***
     * 是否下拉刷新
     * @return
     */
    boolean isPullDownRefresh();


    /***
     * 是否是第一次加载
     * @return
     */
    boolean isFirstLoad();


    /***
     * 是否是第一次加载
     * @param isFirstLoad
     */
    void setIsFirstLoad(boolean isFirstLoad);
}
