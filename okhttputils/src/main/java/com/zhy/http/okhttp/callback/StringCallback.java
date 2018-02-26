package com.zhy.http.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id,int which) throws IOException
    {
        return response.body().string();
    }

    @Override
    public void onResponseHeaders(Response response, int id,int which) {

    }
}
