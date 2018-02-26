package com.open.sina.finance.base.fragment;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

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

public class WeakReferenceHandler <F extends BaseFragment> extends Handler {
    WeakReference<F> weakReferenceHandler;

    public WeakReferenceHandler(F fragment) {
        weakReferenceHandler = new WeakReference<F>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        F fragment = weakReferenceHandler.get();
        if (fragment != null && fragment.isVisible() && fragment.getUserVisibleHint()) {
            fragment.handlerMessage(msg);
            super.handleMessage(msg);
        }
    }

}