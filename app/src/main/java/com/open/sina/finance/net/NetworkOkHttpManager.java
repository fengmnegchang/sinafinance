package com.open.sina.finance.net;

import com.google.gson.Gson;
import com.open.sina.finance.utils.AppUtils;
import com.open.sina.finance.utils.DeviceUtils;
import com.open.sina.finance.utils.EncryptUtils;
import com.open.sina.finance.utils.NetworkUtils;
import com.open.sina.finance.utils.PhoneUtils;
import com.open.sina.finance.utils.SPUtils;
import com.open.sina.finance.utils.ScreenUtils;
import com.open.sina.finance.utils.StringUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;

import org.xutils.common.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * ****************************************************************************************************************************************************************************
 * 网络请求公共管理类
 *
 * @author :fgj
 * @createTime: 2018/1/11.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class NetworkOkHttpManager {


    /***
     *
     * @param tag
     * @param mGenericsCallback
     */
    public static void newsWap(Object tag, GenericsCallback mGenericsCallback) {
        Map<String, String> params = new HashMap<>();

        postString(tag, RequestBuilderID.WAP_ID, RequestCollection.NEWS_CALLBACK, params, mGenericsCallback);
    }



    /****
     * postString 默认header
     * content  params
     * @param tag
     * @param requstId
     * @param url
     * @param params
     * @param mGenericsCallback
     */
    public static synchronized void postString(Object tag, int requstId, String url, Map<String, String> params, GenericsCallback mGenericsCallback) {
        LogUtil.i("cooksidapp===" + SPUtils.getToken());
        Map<String, String> headers = new HashMap<>();
        headers.put("cooksidapp", SPUtils.getToken());

        postString(tag, requstId, url, -1, params, headers, mGenericsCallback);

    }



    /****
     * postString 默认header
     * content  params
     * @param tag
     * @param requstId
     * @param url
     * @param params
     * @param mGenericsCallback
     */
    public static synchronized void postString(Object tag, int requstId, String url, int which, Map<String, String> params, GenericsCallback mGenericsCallback) {
        LogUtil.i("cooksidapp===" + SPUtils.getToken());
        Map<String, String> headers = new HashMap<>();
        headers.put("cooksidapp", SPUtils.getToken());

        postString(tag, requstId, url, which, params, headers, mGenericsCallback);
    }

    /****
     * postString  content 固定 body
     * @param tag
     * @param requstId
     * @param url
     * @param body
     * @param mGenericsCallback
     */
    public static synchronized void postString(Object tag, int requstId, String url, String body, GenericsCallback mGenericsCallback) {
        OkHttpUtils.postString()
                .tag(tag)
                .id(requstId)
                .url(url)
                .addHeader("cooksidapp", SPUtils.getToken())
                .mediaType(MediaType.parse("application/json;charset=UTF-8"))
                .content(body)
                .build()
                .execute(mGenericsCallback);
    }

    /****
     *  postString headers
     * content  params
     * @param tag
     * @param requstId
     * @param url
     * @param which
     * @param params
     * @param headers
     * @param mGenericsCallback
     */

    public static synchronized void postString(Object tag, int requstId, String url, int which, Map<String, String> params, Map<String, String> headers, GenericsCallback mGenericsCallback) {
        Gson gson = new Gson();
        putAppHeader(headers);
        OkHttpUtils.postString()
                .tag(tag)
                .id(requstId)
                .url(url)
                .which(which)
                .headers(headers)
                .mediaType(MediaType.parse("application/json;charset=UTF-8"))
                .content(gson.toJson(params))
                .build()
                .execute(mGenericsCallback);
    }

    /***
     * 存放公共属性 版本等信息
     * @param headers
     */
    public static synchronized  void  putAppHeader(Map<String, String> headers){
        try {
            headers.put("register_source", "ANDROID");
            headers.put("versionname", AppUtils.getAppVersionName());
            headers.put("channel_name", AppUtils.getAppMetaData("UMENG_CHANNEL"));
            headers.put("screensize", ScreenUtils.getScreenWidth()+"x"+ScreenUtils.getScreenHeight());
            headers.put("screendensity", ScreenUtils.getScreenDensity()+"");
            headers.put("screendensitydpi", ScreenUtils.getScreenDensityDpi()+"");
            headers.put("manufacturer", DeviceUtils.getManufacturer());
            headers.put("macaddress", StringUtils.isEmpty(DeviceUtils.getMacAddress())?"00:00:00:00:00:00":DeviceUtils.getMacAddress());
            headers.put("model", DeviceUtils.getModel());
            headers.put("sdkversion", DeviceUtils.getSDKVersionName()+"");
            headers.put("sdkversioncode", DeviceUtils.getSDKVersionCode()+"");
            headers.put("androidid", DeviceUtils.getAndroidID()+"");
            headers.put("isappdebug", AppUtils.isAppDebug()+"");
            headers.put("root", DeviceUtils.isDeviceRooted()+"");
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            headers.put("networktype", NetworkUtils.getNetworkType().name());
            headers.put("imei", PhoneUtils.getIMEI());
        }catch (Exception e){
            e.printStackTrace();
            headers.put("networktype", "UNKNOWN");
            headers.put("imei", "000000000000000");
        }
    }
    /***
     *  上传file
     * @param tag
     * @param requstId
     * @param url
     * @param name
     * @param filename
     * @param file
     * @param which
     * @param params
     * @param mGenericsCallback
     */
    public static synchronized void postFile(Object tag, int requstId, String url, String name, String filename, File file, int which, Map<String, String> params, GenericsCallback mGenericsCallback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("cooksidapp", SPUtils.getToken());

        postFile(tag, requstId, url, name, filename, file, which, params, headers, mGenericsCallback);
    }

    /***
     *  上传file
     * @param tag
     * @param requstId
     * @param url
     * @param name
     * @param filename
     * @param file
     * @param which
     * @param params
     * @param headers
     * @param mGenericsCallback
     */
    public static synchronized void postFile(Object tag, int requstId, String url, String name, String filename, File file, int which, Map<String, String> params, Map<String, String> headers, GenericsCallback mGenericsCallback) {
        Gson gson = new Gson();
        putAppHeader(headers);

        OkHttpUtils.post()
                .addFile(name, filename, file)
                .tag(tag)
                .id(requstId)
                .url(url)
                .which(which)
                .headers(headers)
                .params(params)
                .build()
                .execute(mGenericsCallback);
    }

}
