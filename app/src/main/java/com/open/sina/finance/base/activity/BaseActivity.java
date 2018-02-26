package com.open.sina.finance.base.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.open.sina.finance.view.DrawerBack;
import com.open.sina.finance.view.OnOpenDrawerCompleteListener;

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

public abstract class BaseActivity<BA extends BaseActivity> extends FragmentActivity {
    public WeakActivityReferenceHandler weakReferenceHandler;
    public final String TAG = getClass().getSimpleName();
    protected Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private boolean mIsHideSoftInput;
    private DrawerBack mDrawerBack;

    /***右滑退出activity**/
    private boolean isImplementsDrawerBack = true;

    public ImageLoadingListener getImageLoadingListener() {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityStackManager.getInstance().pushActivity(this);
        if (isImplementsDrawerBack){
            mDrawerBack = new DrawerBack(this);
        }
    }



    public void setActivity(BA ba) {
        this.weakReferenceHandler = new WeakActivityReferenceHandler(ba);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().popActivity(this);
        if (weakReferenceHandler != null) {
            weakReferenceHandler.removeCallbacksAndMessages(null);
            weakReferenceHandler = null;
        }
    }

    /**
     * 是否点击空白处隐藏软键盘
     */
    protected void setHideSoftInput(boolean isHideSoftInput) {
        mIsHideSoftInput = isHideSoftInput;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mIsHideSoftInput) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS
                    );
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 根据 EditText 所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

    /**
     * weak 处理消息
     *
     * @param msg
     */
    public void handlerMessage(Message msg) {

    }

    /***
     * 登陆后，刷新UI
     */
    public void refreshUI() {

    }

    /**
     *
     * 打开或者关闭从左向右滑动代替返回
     * @version:1.0.0
     */
    public void setOnDrawerBackEnabled(boolean enable) {
        if (mDrawerBack != null) {
            mDrawerBack.setOnDrawerBackEnabled(enable);
        }
    }

    /**
     * 监听DrawerCallbacks
     * @version:1.0.0
     */
    public void setOnOpenDrawerCompleteListener(OnOpenDrawerCompleteListener mOnOpenDrawerCompleteListener) {
        if (mDrawerBack != null) {
            mDrawerBack.setOnOpenDrawerCompleteListener(mOnOpenDrawerCompleteListener);
        }
    }

    /**
     * 主动调用滑动返回
     * @version:1.0.0
     */
    public void setBackFinishActivity() {
        if (mDrawerBack != null) {
            mDrawerBack.openDrawer();
        }
    }

    public void setOnBackFacusView(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setOnDrawerBackEnabled(false);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_OUTSIDE:
                    case MotionEvent.ACTION_UP:
                        setOnDrawerBackEnabled(true);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 用滑动返回
     * @version:1.0.0
     */
    public void DrawerBackFinish() {
        if (mDrawerBack != null) {
            mDrawerBack.openDrawer();
        } else {
            finish();
        }
    }

    /***
     * 解决GestureDetector 与DrawerBack 事件冲突
     * @param implementsDrawerBack
     */
    public void setImplementsDrawerBack(boolean implementsDrawerBack) {
        isImplementsDrawerBack = implementsDrawerBack;
    }
}
