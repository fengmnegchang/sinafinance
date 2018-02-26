package com.open.sina.finance.helper;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.open.sina.finance.R;
import com.open.sina.finance.bean.DropItemBean;
import com.open.sina.finance.bean.ImageBean;
import com.open.sina.finance.utils.ScreenUtils;

import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/26.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class PopupWindowManager {
    static PopupWindow popupWindow;

//    /**
//     *
//     * @param activity tabActivity 使用getParent()，及获取tabactivity
//     * @param parent
//     * @param list
//     * @param width 宽度
//     * @param xoff x轴位置
//     * @param yoff y轴位置
//     * @param listener listview监听器
//     */
//    public static void showDropCoinPopupWindow(final Activity activity, View parent, List<DropItemBean> list,int width, int xoff, int yoff, AdapterView.OnItemClickListener listener) {
//        // 加载布局
//        View view = LayoutInflater.from(activity).inflate(R.layout.layout_drop_popup_window, null);
//        DropCoinItemAdapter mDropItemAdapter = new DropCoinItemAdapter(list, activity);
//        ListView listview = (ListView) view.findViewById(R.id.listview);
//        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        listview.setAdapter(mDropItemAdapter);
//        listview.setOnItemClickListener(listener);
//        // 找到布局的控件
//        // 实例化popupWindow
//        popupWindow = new PopupWindow(view,width, (int) ScreenUtils.getIntToDip(activity, (list.size() * 52)));
//        // 控制键盘是否可以获得焦点
//        popupWindow.setFocusable(true);
//        setBackgroundAlpha(activity, 0.5f);//设置屏幕透明度
//        // 设置popupWindow弹出窗体的背景
//        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.gray_popup_drag_shape));
//        popupWindow.showAsDropDown(parent,xoff, yoff);
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // popupWindow隐藏时恢复屏幕正常透明度
//                setBackgroundAlpha(activity, 1.0f);
//            }
//        });
//    }
//
//    /**
//     *
//     * @param activity   tabActivity 使用getParent()，及获取tabactivity
//     * @param parent
//     * @param list
//     * @param width 宽度
//     * @param xoff x轴位置
//     * @param yoff y轴位置
//     * @param listener listview监听器
//     */
//    public static void showDropComparePopupWindow(final Activity activity, View parent, List<DropItemBean> list,int width, int xoff, int yoff, AdapterView.OnItemClickListener listener) {
//        // 加载布局
//        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        View view = LayoutInflater.from(activity).inflate(R.layout.layout_drop_popup_window, null);
//        DropCompareItemAdapter mDropItemAdapter = new DropCompareItemAdapter(list, activity);
//        ListView listview = (ListView) view.findViewById(R.id.listview);
//        listview.setAdapter(mDropItemAdapter);
//        listview.setOnItemClickListener(listener);
//        // 找到布局的控件
//        // 实例化popupWindow
//        popupWindow = new PopupWindow(view,width, (int) ScreenUtils.getIntToDip(activity, (list.size() * 52)));
//        // 控制键盘是否可以获得焦点
//        popupWindow.setFocusable(true);
//        setBackgroundAlpha(activity, 0.5f);//设置屏幕透明度
//        // 设置popupWindow弹出窗体的背景
//        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.gray_popup_drag_shape));
//        popupWindow.showAsDropDown(parent,xoff, yoff);
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // popupWindow隐藏时恢复屏幕正常透明度
//                setBackgroundAlpha(activity, 1.0f);
//            }
//        });
//    }
//
//    /***
//     * 图片分组
//     * @param activity
//     * @param parent
//     * @param groupList
//     */
//    public static void showImageScanPopupWindow(final Activity activity, View parent,List<ImageBean> groupList, AdapterView.OnItemClickListener listener) {
//        // 加载布局
//        View view = LayoutInflater.from(activity).inflate(R.layout.layout_scan_stock_image_popup_window, null);
//        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        ListView listview = (ListView) view.findViewById(R.id.listview);
//        ScanImagePopupAdapter mScanStockImagePopupAdapter = new ScanImagePopupAdapter(groupList,activity,listview);
//        listview.setAdapter(mScanStockImagePopupAdapter);
//        listview.setOnItemClickListener(listener);
////        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                // TODO Auto-generated method stub
////                setBackgroundAlpha(1.0f);
////                if(popupWindow!=null){
////                    popupWindow.dismiss();
////                }
////                list.clear();
////                if((int)id==0){
////                    list.addAll(alllist);
////                }else{
////                    list.addAll(mGruopMap.get(groupList.get(position).getFolderName()));
////                }
////                mChildAdapter.notifyDataSetChanged();
////            }
////        });
//        // 找到布局的控件
//        // 实例化popupWindow
//        popupWindow = new PopupWindow(view, manager.getDefaultDisplay().getWidth(), (int)ScreenUtils.dpToPx(activity,250));
//        // 控制键盘是否可以获得焦点
//        popupWindow.setFocusable(true);
//        setBackgroundAlpha(activity,0.5f);//设置屏幕透明度
//        // 设置popupWindow弹出窗体的背景
//        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.gray_popup_drag_shape));
//
//        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.LEFT, 0,
//                0);
//
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // popupWindow隐藏时恢复屏幕正常透明度
//                setBackgroundAlpha(activity,1.0f);
//            }
//        });
//    }
//
//
//    /***图片相机
//     * @param activity
//     * @param parent
//     * @param listener
//     * @param listener2
//     */
//
//    public static void showPhotoCameraPopupWindow(final Activity activity, View parent, View.OnClickListener listener, View.OnClickListener listener2) {
//        // 加载布局
//        View view = LayoutInflater.from(activity).inflate(R.layout.layout_photo_camera_popup_window, null);
//        TextView txt_photo= view.findViewById(R.id.txt_photo);
//        TextView txt_camera= view.findViewById(R.id.txt_camera);
//
//        txt_photo.setOnClickListener(listener);
//        txt_camera.setOnClickListener(listener2);
//
//        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
//        // 找到布局的控件
//        // 实例化popupWindow
//        popupWindow = new PopupWindow(view, manager.getDefaultDisplay().getWidth(), (int)ScreenUtils.dpToPx(activity,110));
//        // 控制键盘是否可以获得焦点
//        popupWindow.setFocusable(true);
//        setBackgroundAlpha(activity,0.5f);//设置屏幕透明度
//        // 设置popupWindow弹出窗体的背景
//        popupWindow.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.gray_popup_drag_shape));
//
//        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.LEFT, 0,
//                0);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // popupWindow隐藏时恢复屏幕正常透明度
//                setBackgroundAlpha(activity,1.0f);
//            }
//        });
//    }


    public static void popupWindowDismiss(Activity activity) {
        if (popupWindow != null && popupWindow.isShowing()) {
            setBackgroundAlpha(activity,1.0f);
            popupWindow.dismiss();
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }
}
