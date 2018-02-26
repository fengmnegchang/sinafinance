package com.open.sina.finance.helper;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

public class DialogManager {

    static Dialog mDialog;


    /***
     * 确认dialog
     * @param mContext
     * @param title
     * @param msg
     * @param ok
     * @param cancel
     * @param okListener
     * @param cancelListener
     */
    public static void confirmDialog(Context mContext, String title, String msg, String ok, String cancel, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        if (mContext == null)
            return;
        dialogDismiss();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(ok, (okListener == null ? new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } : okListener))
                .setNegativeButton(cancel, cancelListener == null ? new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                } : cancelListener)
                .setCancelable(false);
        mDialog = builder.create();
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
                return true;
            }
        });
        mDialog.show();

    }

//    /**
//     * 通用
//     *
//     * @param mContext
//     * @param title
//     * @param content
//     * @param okListener
//     * @param cancelListener
//     */
//    public static void commonDialog(Context mContext, String title, String content, View.OnClickListener okListener, View.OnClickListener cancelListener) {
//        commonDialog(mContext, title, content, mContext.getResources().getString(R.string.confirm_dialog_ok), mContext.getResources().getString(R.string.confirm_dialog_cancel), okListener, cancelListener);
//    }
//
//    public static void commonDialog(Context mContext, String title, String content, String ok, String cancel, View.OnClickListener okListener, View.OnClickListener cancelListener) {
//        if (mContext == null)
//            return;
//        dialogDismiss();
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_common_dialog, null);
//        TextView tv_title = view.findViewById(R.id.title);
//        TextView tv_content = view.findViewById(R.id.content);
//        TextView tv_cancel = view.findViewById(R.id.cancel);
//        TextView tv_ok = view.findViewById(R.id.ok);
//
//        tv_title.setText(title);
//        tv_content.setText(content);
//        tv_cancel.setText(cancel);
//        tv_ok.setText(ok);
//
//        tv_cancel.setOnClickListener(cancelListener);
//        tv_ok.setOnClickListener(okListener);
//
//        mDialog = new Dialog(mContext);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(view);
//        mDialog.setCancelable(false);
//        mDialog.show();
//
//        Window window = mDialog.getWindow();
//        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_rect_white_dialog));
//        window.setGravity(Gravity.CENTER);
//
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.8f);   //设置宽度充满屏幕
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//
//        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
//                return true;
//            }
//        });
//
//    }
//
//
//    /**
//     * 支付方式
//     *
//     * @param mContext
//     * @param ok
//     * @param cancel
//     * @param mPayTypeAdapter
//     * @param okListener
//     * @param cancelListener
//     * @param itemClickListener
//     */
//    public static void payTypeDialog(Context mContext, String ok, String cancel, PayTypeAdapter mPayTypeAdapter, View.OnClickListener okListener, View.OnClickListener cancelListener, AdapterView.OnItemClickListener itemClickListener) {
//        if (mContext == null)
//            return;
//        dialogDismiss();
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_pay_type_dialog, null);
//        ListView listView = view.findViewById(R.id.listview);
//        TextView iv_cancel = view.findViewById(R.id.iv_cancel);
//        TextView tv_finish = view.findViewById(R.id.tv_finish);
//
//        iv_cancel.setOnClickListener(cancelListener);
//        tv_finish.setOnClickListener(okListener);
//
//        listView.setAdapter(mPayTypeAdapter);
//        listView.setOnItemClickListener(itemClickListener);
//
//        mDialog = new Dialog(mContext);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(view);
//        mDialog.setCancelable(false);
//        mDialog.show();
//
//
//        Window window = mDialog.getWindow();
//        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_rect_white_dialog));
//        //        window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
//        //        window.getDecorView().setPadding(20, 0, 20, 0);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.75f);   //设置宽度充满屏幕
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//
//
//        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
//                return true;
//            }
//        });
//
//    }
//
//    /**
//     * 下单确认
//     *
//     * @param mContext
//     * @param okListener
//     * @param cancelListener
//     */
//    public static void ordersConfirmDialog(Context mContext, String price, String money, String number, String coinCode, View.OnClickListener okListener, View.OnClickListener cancelListener) {
//        if (mContext == null)
//            return;
//        dialogDismiss();
//        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_confirm_dialog, null);
//        TextView tv_price = view.findViewById(R.id.price);
//        TextView tv_money = view.findViewById(R.id.money);
//        TextView tv_number = view.findViewById(R.id.number);
//        TextView remind = view.findViewById(R.id.remind);
//        TextView cancel = view.findViewById(R.id.cancel);
//        TextView ok = view.findViewById(R.id.ok);
//
//        tv_price.setText(price);
//        tv_money.setText(money);
//        tv_number.setText(number);
//        remind.setText(String.format(mContext.getResources().getString(R.string.confirm_dialog_remind_content), coinCode));
//
//        cancel.setOnClickListener(cancelListener);
//        ok.setOnClickListener(okListener);
//
//        mDialog = new Dialog(mContext);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(view);
//        mDialog.setCancelable(false);
//        mDialog.show();
//
//        Window window = mDialog.getWindow();
//        window.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.shape_rect_white_dialog));
//        window.setGravity(Gravity.CENTER);
//
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = (int) (ScreenUtils.getScreenWidth(mContext) * 0.8f);   //设置宽度充满屏幕
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
//
//        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
//                return true;
//            }
//        });
//
//    }

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
