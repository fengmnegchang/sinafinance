package com.open.sina.finance.callback;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.open.sina.finance.base.MessageHandler;
import com.open.sina.finance.json.CommonDataJson;
import com.open.sina.finance.net.HandlerResponseCode;
import com.open.sina.finance.utils.LogUtils;
import com.open.sina.finance.utils.SPUtils;
import com.zhy.http.okhttp.callback.GenericsCallback;
import com.zhy.http.okhttp.callback.IGenericsSerializator;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/12.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public abstract class CommonHandlerGenericsCallback<T extends CommonDataJson> extends GenericsCallback<T> {

    public Handler weakActivityReferenceHandler;
    public Context mContext;

    public CommonHandlerGenericsCallback(Context mContext, IGenericsSerializator serializator, Handler weakActivityReferenceHandler) {
        super(serializator);
        this.mContext = mContext;
        this.weakActivityReferenceHandler = weakActivityReferenceHandler;
    }

    @Override
    public void onError(Call call, Exception e, int id,int which) {
        LogUtils.e("CommonHandlerGenericsCallback","onError");
        e.printStackTrace();
        HandlerResponseCode.onHandlerError(call, e, id,which);
        weakActivityReferenceHandler.sendEmptyMessage(MessageHandler.GENERICS_CALL_BACK_ERROR);
        weakActivityReferenceHandler.sendEmptyMessage(MessageHandler.REFRESH_COMPLETE3);
    }

    @Override
    public T parseNetworkResponse(Response response, int id,int which) throws IOException {
        String string = response.body().string();
        Gson gson = new Gson();
        CommonDataJson result = gson.fromJson(string,CommonDataJson.class);
        T bean;
        if (result!=null && ("200".equals(result.getResult()) || "40000".equals(result.getCode()))){
            Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        if (entityClass == String.class) {
//            return (T) string;
//        }
            bean = mGenericsSerializator.transform(string, entityClass);
        }else {
            bean = (T) gson.fromJson(string,CommonDataJson.class);;
        }
        return bean;
    }
    @Override
    public void onResponseHeaders(Response response, int id,int which) {
        LogUtils.i("CommonHandlerGenericsCallback","onResponse");
        //headers
        if (response.headers()!=null && response.headers().size()>0){
            Set<String> keys = response.headers().names();
            for( String key : keys ){
                String val = response.header(key);
                LogUtils.i("CommonHandlerGenericsCallback", "response headers key==="+key+";val=="+val);
            }
            LogUtils.i("CommonHandlerGenericsCallback Set-Cookie",response.header("Set-Cookie",""));
            if (response.header("cooksidapp","")!=null && response.header("cooksidapp","").length()>0){
                SPUtils.setToken(response.header("cooksidapp",""));
                LogUtils.i("CommonHandlerGenericsCallback cooksidapp",response.header("cooksidapp",""));
            }
        }
    }

    @Override
    public void onResponse(T response, int id,int which) {
        LogUtils.i("CommonHandlerGenericsCallback","onResponse");
        if (HandlerResponseCode.onHandlerResponse(mContext,response,id,which)){
            weakActivityReferenceHandler.sendMessage(weakActivityReferenceHandler.obtainMessage(MessageHandler.GENERICS_CALL_BACK_RESPONSE0,id,which,response));
        }else{
            //处理
            weakActivityReferenceHandler.sendMessage(weakActivityReferenceHandler.obtainMessage(MessageHandler.GENERICS_CALL_BACK_RESPONSE1,id,which,response));
        }
        weakActivityReferenceHandler.sendEmptyMessage(MessageHandler.REFRESH_COMPLETE3);
    }
}
