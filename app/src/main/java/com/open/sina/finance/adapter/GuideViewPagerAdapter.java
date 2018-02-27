package com.open.sina.finance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.open.sina.finance.R;
import com.open.sina.finance.activity.MainTabActivity;
import com.open.sina.finance.base.adapter.CommonPagerAdapter;
import com.open.sina.finance.bean.MainTabBean;
import com.open.sina.finance.utils.ActivityUtils;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/26.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class GuideViewPagerAdapter extends CommonPagerAdapter<MainTabBean> {
    public GuideViewPagerAdapter(Context mContext, List<MainTabBean> list) {
        super(mContext, list);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final MainTabBean bean = (MainTabBean) getItem(position);
        final ViewHolder mViewHolder = new ViewHolder();
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_dot_viewpager, null);
        mViewHolder.draweeview = (ImageView) convertView.findViewById(R.id.draweeview);
        mViewHolder.img_tomain = (ImageView) convertView.findViewById(R.id.img_tomain);
        if (bean != null) {
            mViewHolder.draweeview.setImageResource(bean.resId);
            if (bean.isEnd){
                mViewHolder.img_tomain.setVisibility(View.VISIBLE);
            }else {
                mViewHolder.img_tomain.setVisibility(View.GONE);
            }
            mViewHolder.img_tomain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //to maintab
                    ActivityUtils.startActivity(MainTabActivity.class);
                }
            });
        }
        container.addView(convertView);
        return convertView;
    }

    private class ViewHolder {
        ImageView draweeview,img_tomain;
    }
}
