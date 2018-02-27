//package com.open.sina.finance.jsoup.news;
//
//import com.open.sina.finance.bean.news.ArticleBean;
//import com.open.sina.finance.jsoup.CommonService;
//import com.open.sina.finance.net.RequestCollection;
//import com.open.sina.finance.utils.LogUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
///**
// * ****************************************************************************************************************************************************************************
// *
// * @author :fgj
// * @createTime: 2018/2/27.
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description: ****************************************************************************************************************************************************************************
// */
//
//public class ArticleJsoupService extends CommonService {
//
//    /***
//     * 头条
//     */
//    public static List<ArticleBean> parseArticle(String href, int pageNo) {
//        List<ArticleBean> list = new ArrayList<ArticleBean>();
//        try {
//            // href = makeURL(href, new HashMap<String, Object>() {
//            // {
//            // }
//            // });
//            Document doc;
//            doc = Jsoup.connect(href).userAgent(RequestCollection.userAgentMoblie).timeout(10000).get();
//            LogUtils.e(TAG, "url = " + href);
//
//            // Document doc =
//            // Jsoup.connect(href).userAgent(UrlUtils.userAgent).timeout(10000).get();
//            // System.out.println(doc.toString());
//            try {
//                /**
//                 */
//                Element globalnavElement = doc.select("div.seo_data_list").first();
//                Elements moduleElements = doc.select("li");
//                if (moduleElements != null && moduleElements.size() > 0) {
//                    for (int i = 0; i < moduleElements.size(); i++) {
//                        ArticleBean sbean = new ArticleBean();
//                        try {
//                            try {
//                                Element aElement = moduleElements.get(i).select("a").first();
//                                if (aElement != null) {
//                                    String hrefa = aElement.attr("href");
//                                    LogUtils.e(TAG, "i==" + i + ";hrefa==" + hrefa);
//                                    sbean.setDataoriginalurl(hrefa);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                            try {
//                                Element imgElement = moduleElements.get(i).select("a").first();
//                                if (imgElement != null) {
//                                    String title = imgElement.text();
//                                    LogUtils.e(TAG, "i==" + i + ";title==" + title);
//                                    sbean.setTitle(title);
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
////                            try {
////                                Element imgElement = moduleElements.get(i).select("span.flag").first();
////                                if (imgElement != null) {
////                                    String flag = imgElement.text();
////                                    LogUtils.e(TAG, "i==" + i + ";flag==" + flag);
////                                    sbean.setFlag(flag);
////                                }
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
////
////                            try {
////                                Element imgElement = moduleElements.get(i).select("img").first();
////                                if (imgElement != null) {
////                                    String src = imgElement.attr("src");
////                                    LogUtils.e(TAG, "i==" + i + ";src==" + src);
////                                    sbean.setSrc(src);
////                                }
////                            } catch (Exception e) {
////                                e.printStackTrace();
////                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                        list.add(sbean);
//                    }
//
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//}
