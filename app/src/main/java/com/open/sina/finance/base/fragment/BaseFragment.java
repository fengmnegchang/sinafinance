package com.open.sina.finance.base.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

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

public abstract class BaseFragment<F extends BaseFragment>  extends Fragment {
    public final   String TAG = getClass().getSimpleName();
    public WeakReferenceHandler weakReferenceHandler;
    public boolean isVisibleToUser;
    public int pageStart = 0;

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

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
//    /***
//     * 设置布局layoutid
//     * @return
//     */
//    public abstract int getLayoutResId();
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(getLayoutResId(), container, false);
//        findView();
//        return view;
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initValue();
        bindEvent();
    }

    public void setFragment(F f) {
        weakReferenceHandler = new WeakReferenceHandler(f);
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        initUI(isVisibleToUser);
//    }
//
//    private boolean isFirst = true;
//    protected void initUI(final boolean isVisibleToUser) {
//        if (weakReferenceHandler != null) {
//            weakReferenceHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (getActivity() == null || !isVisibleToUser) {
//                        initUI(isVisibleToUser);
//                    } else {
//                        if(isFirst){
//                            weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.QUERY_CACHE_FROM_LOCAL, 50);
//                            isFirst = false;
//                        }
//                    }
//                }
//            }, 200);
//        }
//    }

    /**
     * weak 处理消息
     * @param msg
     */
    public void handlerMessage(Message msg){

    }

//    /***
//     * onCreateView
//     */
//    public void findView(){
//
//    }

    /***
     *
     */
    public void initValue(){

    }

    /**
     *  绑定事件，在onActivityCreated调用
     */
    public void bindEvent(){

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            if (weakReferenceHandler!=null){
                weakReferenceHandler.removeCallbacksAndMessages(null);
                weakReferenceHandler = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
