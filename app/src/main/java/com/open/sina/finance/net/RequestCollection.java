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
    public static String SWAGGER_API_URL = isDebug() ? "https://finance.sina.cn/" : "https://finance.sina.cn/";
    public static final String UPDATE_URL="https://raw.githubusercontent.com/WVector/AppUpdate/master/json/json.txt";
    public static final String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
    public static final String userAgentMoblie = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36";

    /***wap***/
    public static final String WAP = SWAGGER_API_URL + "?from=wap";


    public static final String NEWS_CALLBACK = "https://cre.dp.sina.cn/api/v3/get?callback=jQuery214019434993138426582_1519718009366&cateid=y&cre=tianyi&mod=wfin&merge=3&statics=1&impress_id=%2C&action=0&up=4&down=1&tm=1519717993&ad=%7B%22rotate_count%22%3A603%2C%22page_url%22%3A%22https%253A%252F%252Ffinance.sina.cn%252F%253Ffrom%253Dwap%22%2C%22channel%22%3A%22tianyi_wfin%22%2C%22platform%22%3A%22wap%22%7D&_=1519718009367";

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        RequestCollection.isDebug = isDebug;
    }
}
