package com.open.sina.finance.adapter.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.open.sina.finance.R;
import com.open.sina.finance.base.adapter.CommonAdapter;
import com.open.sina.finance.base.adapter.ViewHolder;
import com.open.sina.finance.bean.news.ArticleBean;

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

public class NewsAdapter extends CommonAdapter<ArticleBean, NewsAdapter.NewsHodler> {
    public NewsAdapter(List<ArticleBean> list, Context mContext) {
        super(list, mContext);
    }

    @Override
    public NewsHodler createHolder(int position, ViewGroup parent) {
        return new NewsHodler(LayoutInflater.from(getContext()).inflate(R.layout.adapter_news, parent, false));
    }

    @Override
    public void bindData(int position, NewsHodler holder, ArticleBean data) {
        if (data!=null){
            if (data.getMthumbs()!= null && data.getMthumbs().size() > 0) {
                DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.ic_launcher).showImageForEmptyUri(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
    //						.cacheInMemory().cacheOnDisc().build();
                        .cacheInMemory().cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED).build();
                ImageLoader.getInstance().displayImage(data.getMthumbs().get(0), holder.img_pic, options, getImageLoadingListener());
            }
            holder.txt_flag.setText(data.getMedia());
            holder.txt_title.setText(data.getMtitle());
        }

    }

    public class NewsHodler extends ViewHolder {
        TextView txt_title;
        TextView txt_flag;
        ImageView img_pic;

        public NewsHodler(View view) {
            super(view);
            this.txt_title = (TextView) view.findViewById(R.id.txt_title);
            this.txt_flag = (TextView) view.findViewById(R.id.txt_flag);
            this.img_pic = (ImageView) view.findViewById(R.id.img_pic);
        }
    }


}
