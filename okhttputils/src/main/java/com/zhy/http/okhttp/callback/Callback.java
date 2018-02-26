package com.zhy.http.okhttp.callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback<T>
{
    /**
     * UI Thread
     *
     * @param request
     */
    public void onBefore(Request request, int id,int which)
    {
    }

    /**
     * UI Thread
     *
     * @param
     */
    public void onAfter(int id,int which)
    {
    }

    /**
     * UI Thread
     *
     * @param progress
     */
    public void inProgress(float progress, long total , int id,int which)
    {

    }

    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateReponse(Response response, int id,int which)
    {
        return response.isSuccessful();
    }

    /**
     * Thread Pool Thread
     * @param response 响应
     * @param id 请求序列id
     * @param which 标记列表位置position msg.rg2
     * @return 泛型T对象
     * @throws Exception
     */
    public abstract T parseNetworkResponse(Response response, int id,int which) throws Exception;

    /***
     *
     * @param call
     * @param e
     * @param id 请求序列id
     * @param which 标记列表位置position msg.rg2
     */
    public abstract void onError(Call call, Exception e, int id,int which);

    /***
     *  响应
     * @param response  响应
     * @param id 请求序列id
     * @param which 标记列表位置position msg.rg2
     */
    public abstract void onResponse(T response, int id,int which);

    /****
     *  响应头header
     * @param response 响应
     * @param id 请求序列id
     * @param which 标记列表位置position msg.rg2
     */
    public abstract void onResponseHeaders(Response response, int id,int which);

    public static Callback CALLBACK_DEFAULT = new Callback()
    {
        @Override
        public void onResponseHeaders(Response response, int id,int which) {

        }

        @Override
        public Object parseNetworkResponse(Response response, int id,int which) throws Exception
        {
            return null;
        }

        @Override
        public void onError(Call call, Exception e, int id,int which)
        {

        }

        @Override
        public void onResponse(Object response, int id,int which)
        {

        }
    };

}