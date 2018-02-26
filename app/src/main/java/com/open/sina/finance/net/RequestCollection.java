package com.open.sina.finance.net;

/**
 * ****************************************************************************************************************************************************************************
 * 接口请求地址管理
 *
 * @author :fgj
 * @createTime: 2018/1/10.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class RequestCollection {
    /***接口环境**/
    public static boolean isDebug = true;
    public static String SWAGGER_API_URL = isDebug() ? "http://47.97.109.220:8081/" : "http://47.97.109.220:8080/";
    public static final String UPDATE_URL="https://raw.githubusercontent.com/WVector/AppUpdate/master/json/json.txt";
     /***注册 校验用户名是否存在***/
    public static final String ISUSERNAMEEXISTED = SWAGGER_API_URL + "user/isUsernameExisted.do";



    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        RequestCollection.isDebug = isDebug;
    }
}
