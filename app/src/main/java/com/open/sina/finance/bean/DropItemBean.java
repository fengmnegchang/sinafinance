package com.open.sina.finance.bean;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/26.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class DropItemBean extends CommonBean {
    public String itemName;
    public int itemId;
    public boolean isSelected;

    public DropItemBean(String itemName, int itemId,boolean isSelected) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.isSelected = isSelected;
    }
}
