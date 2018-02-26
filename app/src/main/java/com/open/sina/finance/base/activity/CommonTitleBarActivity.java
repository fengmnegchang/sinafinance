package com.open.sina.finance.base.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.open.sina.finance.R;
import com.open.sina.finance.utils.ActivityUtils;
import com.open.sina.finance.utils.ScreenUtils;
import com.open.sina.finance.view.OnOpenDrawerCompleteListener;


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

public class CommonTitleBarActivity extends BaseActivity implements View.OnClickListener,OnOpenDrawerCompleteListener {

    /* 是否需要该父类所有布局 */
    private boolean isExtendsCommonTitleBarActivity = true;

    public TextView txt_left;// 左文字
    public TextView txt_title;// 中标题
    public TextView txt_right;// 右文字
    public ImageView id_iv_left;// 左图片
    public ImageView id_iv_right;// 右图片
    public RelativeLayout layout_titlebar;
    public TextView txt_time;//中间底部刷新时间
    public ImageView id_iv_right2;//右侧图片2

    protected View commonContentBg;
    /* android 4.4+上状态栏默认透明 */
    protected boolean isFlagTranslucentStatus = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isFlagTranslucentStatus) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setOnOpenDrawerCompleteListener(this);

        if(isExtendsCommonTitleBarActivity){
            setContentView(R.layout.activity_common_titlebar);
            txt_left = (TextView) findViewById(R.id.txt_left);
            txt_title = (TextView) findViewById(R.id.txt_title);
            txt_right = (TextView) findViewById(R.id.txt_right);

            id_iv_left = (ImageView) findViewById(R.id.id_iv_left);
            id_iv_right = (ImageView) findViewById(R.id.id_iv_right);
            layout_titlebar = (RelativeLayout) findViewById(R.id.layout_titlebar);

            txt_time = (TextView) findViewById(R.id.txt_time);
            id_iv_right2 = (ImageView) findViewById(R.id.id_iv_right2);

            if(txt_title!=null){
                txt_title.setOnClickListener(this);
            }

            if(id_iv_right!=null){
                id_iv_right.setOnClickListener(this);
            }
            if(id_iv_left!=null){
                id_iv_left.setOnClickListener(this);
            }
            if(txt_left!=null){
                txt_left.setOnClickListener(this);
            }
            if(txt_right!=null){
                txt_right.setOnClickListener(this);
            }

            if(id_iv_right2!=null){
                id_iv_right2.setOnClickListener(this);
            }
        }
    }


    /**
     * 添加中间布局
     */
    protected void addContentView(int layoutResId) {
        if (isExtendsCommonTitleBarActivity) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            commonContentBg = inflater.inflate(layoutResId, null);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.gravity = Gravity.TOP | Gravity.LEFT;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && layout_titlebar != null && isFlagTranslucentStatus) {
                int statusBarHeight = ScreenUtils.getStatusHeight(this);
                int layoutHeight = (statusBarHeight > 0 ? statusBarHeight : 36) + (int) ScreenUtils.getIntToDip(45,this);
                layout_titlebar.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, layoutHeight));
                layout_titlebar.setPadding(0, statusBarHeight, 0, 0);
                lp.topMargin = layoutHeight;
            } else {
                lp.topMargin =  (int) ScreenUtils.getIntToDip(45,this);
            }
            addContentView(commonContentBg, lp);
            init();
        }
    }

    /**
     * 设置中间布局 无顶部条
     */
    protected void setContentLayout(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        commonContentBg = inflater.inflate(layoutResId, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.TOP | Gravity.LEFT;
        addContentView(commonContentBg, lp);
        init();
    }


    /**
     * 设置中间布局 无顶部条
     */
    protected void setContentLayout(int layoutResId,boolean isPadding) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        commonContentBg = inflater.inflate(layoutResId, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.TOP | Gravity.LEFT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = ScreenUtils.getStatusHeight(this);
            commonContentBg.setPadding(0, statusBarHeight, 0, 0);
        } else {
        }
        addContentView(commonContentBg, lp);
        init();
    }


    public void setLeftTextVisivable(boolean visiable) {
        if (txt_left != null) {
            if (visiable) {
                txt_left.setVisibility(View.VISIBLE);
                id_iv_left.setVisibility(View.GONE);
            } else {
                txt_left.setVisibility(View.GONE);
                id_iv_left.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setLeftTextValue(String textvalue) {
        if (txt_left != null) {
            txt_left.setText(textvalue);
        }
    }

    public void setRightTextVisivable(boolean visiable) {
        if (txt_right != null) {
            if (visiable) {
                txt_right.setVisibility(View.VISIBLE);
                id_iv_right.setVisibility(View.GONE);
            } else {
                txt_right.setVisibility(View.GONE);
                id_iv_right.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setRightTextValue(String textvalue) {
        if (txt_right != null) {
            txt_right.setText(textvalue);
        }
    }

    /**
     * 中间 标题
     */
    public void setCenterTextValue(String textvalue) {
        if (txt_title != null) {
            txt_title.setText(textvalue);
        }
    }

    /**
     * 中间右侧图标
     */
    public void setCenterCompoundDrawables(int resId){
        if (txt_title != null) {
            txt_title.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(resId),null);
        }
    }

    /**
     * 中间底部刷新时间
     */
    public void setCenterTimeTextValue(String textvalue) {
        if (txt_time != null) {
            txt_time.setText(textvalue);
            txt_time.setVisibility(View.VISIBLE);
        }
    }

    public void setLeftVisivableGone() {
        txt_left.setVisibility(View.GONE);
        id_iv_left.setVisibility(View.GONE);
    }

    public void setRightVisivableGone() {
        txt_right.setVisibility(View.GONE);
        id_iv_right.setVisibility(View.GONE);
    }

    public void setLeftImageResId(int resid) {
        id_iv_left.setVisibility(View.VISIBLE);
        id_iv_left.setImageResource(resid);
    }

    /**
     * 标题栏背景色
     */
    public void setStatusBarColor(int color) {
        if (layout_titlebar != null) {
            layout_titlebar.setBackgroundColor(color);
        }
    }

    public void setStatusBarDrawable(Drawable drawable) {
        if (layout_titlebar != null) {
            layout_titlebar.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 右侧图标 默认刷新功能
     */
    public void setRightImageResId(int resid) {
        id_iv_right.setVisibility(View.VISIBLE);
        id_iv_right.setImageResource(resid);
    }

    /**
     * 右侧图标2 默认搜索按钮
     */
    public void setRightImage2ResId(int resid) {
        id_iv_right2.setVisibility(View.VISIBLE);
        id_iv_right2.setImageResource(resid);
    }


    @Override
    public void onClick(View v) {

    }

    public void init(){
        try {
            initControl();
            initValue();
            bindEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取控件
     */
    public void  initControl(){};

    /***
     * 赋值
     */
    public void initValue(){};

    /***
     * 绑定事件
     */
    public void bindEvent(){};

    /**
     * android 4.4+上状态栏是否透明
     * @param isFlagTranslucentStatus
     * @description: 在super.onCreat() 之前调用
     */
    public void setIsFlagTranslucentStatus(boolean isFlagTranslucentStatus) {
        this.isFlagTranslucentStatus = isFlagTranslucentStatus;
    }

    public void setExtendsCommonTitleBarActivity(boolean extendsCommonTitleBarActivity) {
        isExtendsCommonTitleBarActivity = extendsCommonTitleBarActivity;
    }

    @Override
    public void onOpenDrawerComplete() {
        ActivityUtils.finishActivity(this);
    }

    @Override
    public boolean onMoveRight() {
        return false;
    }
}
