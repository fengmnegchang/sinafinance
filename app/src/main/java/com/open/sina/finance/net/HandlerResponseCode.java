package com.open.sina.finance.net;

import android.content.Context;
import android.util.Log;


import com.open.sina.finance.json.CommonDataJson;
import com.open.sina.finance.utils.ToastUtils;

import okhttp3.Call;

/**
 * ****************************************************************************************************************************************************************************
 * 公共状态封装code状态
 * @author :fgj
 * @createTime: 2018/1/11.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class HandlerResponseCode {
    public static String TAG = "HandlerResponseCode";

    /****
     * 公共状态封装 浏览用户，登陆，
     * @return
     */
    public static boolean onHandlerResponse(Context mContext, CommonDataJson response, int id, int which){
        Log.d(TAG,"onHandlerResponse");
        boolean isStatus = false;
        if (response!=null && response.getResult()!=null) {
            switch (response.getResult()) {
                case "200":
                    //Success
                    isStatus = true;
                    break;
                case "500":
                    //Internal Server Error
                    ToastUtils.showShort(response.getMsg());
                case "904":
                    //手机验证码错误！
                    ToastUtils.showShort(response.getMsg());
                    break;
                case "400":
                    //请求参数错误
                    ToastUtils.showShort(response.getMsg());
                    break;
                case "402":
                    //用户未登录
                    ToastUtils.showShort(response.getMsg());
                    break;
                case "404":
                    //操作失败
                    ToastUtils.showShort(response.getMsg());
                    break;
                case "401":
                    //数据为空
//                    ToastUtils.showShort(response.getMsg());
                    break;
                default:
                    ToastUtils.showShort(response.getMsg());
                    break;
            }
        }
        if (response!=null && response.getCode()!=null && "40000".equals(response.getCode())) {
            //图片上传
            isStatus = true;
        }
        return isStatus;
    }

    /***
     * 公共error封装
     */
    public static void onHandlerError(Call call, Exception e, int id,int which){
        //判断exception类型，对应状态
        Log.d(TAG,"onHandlerError=="+e.toString());
        e.printStackTrace();
        //java.net.SocketTimeoutException
        //java.net.ConnectException: Failed to connect to /47.97.109.220:8080
        //java.io.IOException: Canceled
        if (!e.toString().contains("java.io.IOException: Canceled")){
            ToastUtils.showShort(e.toString());
        }
    }

}
