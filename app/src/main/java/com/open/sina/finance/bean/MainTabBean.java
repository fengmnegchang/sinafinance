package com.open.sina.finance.bean;

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

public class MainTabBean extends CommonBean{
    public int resId;
    public String tabName;


    public MainTabBean(int resId, String tabName) {
        this.resId = resId;
        this.tabName = tabName;
    }

}
