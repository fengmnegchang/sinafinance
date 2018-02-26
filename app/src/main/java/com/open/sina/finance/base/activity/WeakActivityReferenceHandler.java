package com.open.sina.finance.base.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/10.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class WeakActivityReferenceHandler extends Handler {
    WeakReference<BaseActivity> weakReferenceHandler;

    public WeakActivityReferenceHandler(BaseActivity activity) {
        weakReferenceHandler = new WeakReference<BaseActivity>(activity);
    }

    @SuppressLint("NewApi")
    @Override
    public void handleMessage(Message msg) {
        BaseActivity activity = weakReferenceHandler.get();
        if (activity != null && !activity.isFinishing()) {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (!activity.isDestroyed()) {
                    activity.handlerMessage(msg);
                    super.handleMessage(msg);
                }
            } else {
                activity.handlerMessage(msg);
                super.handleMessage(msg);
            }

        }
    }
}