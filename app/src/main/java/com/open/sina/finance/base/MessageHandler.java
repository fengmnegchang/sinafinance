package com.open.sina.finance.base;

/**
 * ****************************************************************************************************************************************************************************
 * handle code 码大于10000；小于10000为接口code 码
 * @author :Administrator
 * @createTime: 2018/1/5
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class MessageHandler {
    //handle code 码大于10000；小于10000为接口code 码
    /** 刷新 */
    public static final int MESSAGE_HANDLER = 10000;
    /** 请求数据成功 */
    public static final int REFRESH_COMPLETE3 = 10001;
    /** 默认位置 */
    public static final int MESSAGE_DEFAULT_POSITION = 10002;
    /** pull down */
    public static final int REFRESH_PULL_DOWN = 10003;
    /** pull up  */
    public static final int REFRESH_PULL_UP = 10004;
    /** 刷新错误 */
    public static final int REFRESH_ERROR = 10005;
    /** 刷新view */
    public static final int REFRESHING_VIEW = 10006;
    /** 请求数据成功 */
    public static final int REFRESH_COMPLETE = 10007;

    /** 清除本地用户数据 */
    public static final int REFRESH_CLEAR_USER_CACHE = 10008;

    /** 查询sql缓存  */
    public static final int QUERY_CACHE_FROM_LOCAL = 30001;
    /** 查询缓存成功 */
    public static final int QUERY_CACHE_COMPLETE = 30002;

    /****公共封装回调 onerror**/
    public static final int GENERICS_CALL_BACK_ERROR =  40000;
    /****公共封装回调 onResponse**/
    public static final int GENERICS_CALL_BACK_RESPONSE0 =  40001;
    /****公共封装回调 onResponse1**/
    public static final int GENERICS_CALL_BACK_RESPONSE1=  40002;


    /****adapter回调activity，fragment 事件**/
    public static final int ADAPTER_CALL_BACK_ON_ITEN_CLICK=  50000;

}
