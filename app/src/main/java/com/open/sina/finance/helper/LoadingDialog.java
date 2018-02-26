package com.open.sina.finance.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.open.sina.finance.R;
import com.open.sina.finance.utils.ScreenUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/17.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class LoadingDialog {

    static Dialog mDialog;


    /***
     * 加载loading
     * @param mContext
     */
    public static void dialogLoading(Activity mContext) {
        if (mContext == null)
            return;
        dialogDismiss();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_dialog, null);
        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.show();

        Window window = mDialog.getWindow();
        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_rect_white_dialog));
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75f);   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
                return true;
            }
        });
    }

    public static void dialogDismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }


    public static boolean isShowing() {
        if (mDialog != null && mDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }
}
