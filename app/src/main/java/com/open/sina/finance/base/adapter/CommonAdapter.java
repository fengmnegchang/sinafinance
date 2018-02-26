package com.open.sina.finance.base.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Administrator
 * @createTime: 2018/1/5
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public abstract class CommonAdapter<T,VH extends ViewHolder>  extends BaseAdapter {
    public final String TAG = getClass().getSimpleName();
    private List<T> list = new ArrayList<T>();
    private Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public CommonAdapter(List<T> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final VH holder;
        if (convertView == null) {
            holder = createHolder(position, parent);
            convertView = holder.itemView;
        } else {
            holder = (VH) convertView.getTag();
        }
        bindData(position, holder, getItem(position));
        return convertView;
    }

    public abstract VH createHolder(int position, ViewGroup parent);

    public abstract void bindData(int position, VH holder, T data);


    public Context getContext() {
        return mContext;
    }

    protected ImageLoadingListener getImageLoadingListener() {
        return animateFirstListener;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
        public static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
