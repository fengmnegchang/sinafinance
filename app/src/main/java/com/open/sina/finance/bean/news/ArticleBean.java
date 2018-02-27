package com.open.sina.finance.bean.news;

import com.open.sina.finance.bean.CommonBean;

import java.util.List;

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

public class ArticleBean extends CommonBean {
    private String mtitle;
    private String media;
    private List<String> mthumbs;
    private String url;
    private String comment_count;

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public List<String> getMthumbs() {
        return mthumbs;
    }

    public void setMthumbs(List<String> mthumbs) {
        this.mthumbs = mthumbs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }
}
