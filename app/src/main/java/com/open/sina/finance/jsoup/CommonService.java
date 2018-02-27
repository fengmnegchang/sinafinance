package com.open.sina.finance.jsoup;

import com.open.sina.finance.utils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/27.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class CommonService {
    public  static final String TAG = CommonService.class.getSimpleName();

    public static String makeURL(String p_url, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(p_url);
        if (url.indexOf("?") < 0)
            url.append('?');
        for (String name : params.keySet()) {
            url.append('&');
            url.append(name);
            url.append('=');
            url.append(String.valueOf(params.get(name)));
            // 不做URLEncoder处理
            // url.append(URLEncoder.encode(String.valueOf(params.get(name)),
            // UTF_8));
        }
        return url.toString().replace("?&", "?");
    }

    public static void parse(String href) {
        try {
            href = makeURL(href, new HashMap<String, Object>() {
                {
                }
            });
            LogUtils.e(TAG, "url = " + href);

            Document doc = Jsoup.connect(href)
                    // .userAgent(UrlUtils.qianbailuAgent)
                    .timeout(10000).get();
            System.out.println(doc.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
