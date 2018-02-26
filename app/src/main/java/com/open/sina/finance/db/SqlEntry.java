package com.open.sina.finance.db;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * ****************************************************************************************************************************************************************************
 *  本地数据库缓存
 * @author :fgj
 * @createTime: 2018/1/12.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
@Table(name = "cachetable")
public class SqlEntry {
    public static final String COLUMN_CACHE_TYPE_OFFLINE_JSON = "OFFLINE_JSON";


    public static final String COLUMN_WHICH_PAGE = "whichpage";
    public static final String COLUMN_USER_ID = "userid";
    public static final String COLUMN_JSON= "json";
    public static final String COLUMN_CACHE_TYPE = "cachetype";


    @Column(name = "id", isId = true)
    private int id;

    @Column(name = COLUMN_WHICH_PAGE)
    private String whichpage;

    @Column(name = COLUMN_USER_ID)
    private String userid;

    @Column(name = COLUMN_JSON)
    private String json;

    @Column(name = COLUMN_CACHE_TYPE)
    private String cachetype;

    public String getWhichpage() {
        return whichpage;
    }

    public void setWhichpage(String whichpage) {
        this.whichpage = whichpage;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getCachetype() {
        return cachetype;
    }

    public void setCachetype(String cachetype) {
        this.cachetype = cachetype;
    }

    @Override
    public String toString() {
        return "cachetable{" +
                "id=" + id +
                ", "+COLUMN_WHICH_PAGE+"='" + whichpage + '\'' +
                ", "+COLUMN_USER_ID+"='" + userid + '\''+
                ", "+COLUMN_JSON+"='" + json + '\''+
                ", "+COLUMN_CACHE_TYPE+"='" + cachetype + '\'' +
                '}';
    }

}
