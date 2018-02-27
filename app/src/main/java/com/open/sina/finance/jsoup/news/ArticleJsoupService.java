package com.open.sina.finance.jsoup.news;

import com.open.sina.finance.bean.IndicatorBean;
import com.open.sina.finance.bean.news.ArticleBean;
import com.open.sina.finance.jsoup.CommonService;
import com.open.sina.finance.net.RequestCollection;
import com.open.sina.finance.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

public class ArticleJsoupService extends CommonService {

    /***
     * 头条
     */
    public static List<IndicatorBean> parseIndicator(String href, int pageNo) {
        List<IndicatorBean> list = new ArrayList<IndicatorBean>();
        try {
            // href = makeURL(href, new HashMap<String, Object>() {
            // {
            // }
            // });
            Document doc;
            doc = Jsoup.connect(href).userAgent(RequestCollection.userAgentMoblie).timeout(10000).get();
            LogUtils.e(TAG, "url = " + href);

            // Document doc =
            // Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
            // System.out.println(doc.toString());
            try {
                /**
                 */
                Element globalnavElement = doc.select("section.module-nav").first();
                Elements moduleElements = globalnavElement.select("a.nav-item");
                if (moduleElements != null && moduleElements.size() > 0) {
                    for (int i = 0; i < moduleElements.size(); i++) {
                        IndicatorBean sbean = new IndicatorBean("");
                        try {
                            try {
                                Element aElement = moduleElements.get(i).select("a").first();
                                if (aElement != null) {
                                    String hrefa = aElement.attr("href");
                                    LogUtils.e(TAG, "i==" + i + ";hrefa==" + hrefa);
                                    sbean.setHref(hrefa);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            try {
                                Element imgElement = moduleElements.get(i).select("a").first();
                                if (imgElement != null) {
                                    String title = imgElement.text();
                                    LogUtils.e(TAG, "i==" + i + ";title==" + title);
                                    sbean.setInName(title);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        list.add(sbean);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
