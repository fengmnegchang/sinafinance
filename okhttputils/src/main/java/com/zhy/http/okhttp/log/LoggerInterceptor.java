package com.zhy.http.okhttp.log;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by zhy on 16/3/1.
 */
public class LoggerInterceptor implements Interceptor {
    public String tag = LoggerInterceptor.class.getSimpleName();
    public boolean isDebug = true;

    public LoggerInterceptor(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public LoggerInterceptor(String tag) {
        this.tag = tag;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response);
    }

    private Response logForResponse(Response response) {
        try {
            //===>response log
           e(tag, "========response'log=======");
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
           e(tag, "url : " + clone.request().url());
           e(tag, "code : " + clone.code());
           e(tag, "protocol : " + clone.protocol());
            if (!TextUtils.isEmpty(clone.message()))
               e(tag, "message : " + clone.message());

            //headers
            if (clone.headers()!=null && clone.headers().size()>0){
                Set<String> keys = clone.headers().names();
                for( String key : keys ){
                    String val = clone.header(key);
                    e(tag, "response headers key==="+key+";val==="+val);
                }
            }

            ResponseBody body = clone.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                   e(tag, "responseBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        String resp = body.string();
                       e(tag, "responseBody's content : " + resp);

                        body = ResponseBody.create(mediaType, resp);
                        return response.newBuilder().body(body).build();
                    } else {
                       e(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }


           e(tag, "========response'log=======end");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

           e(tag, "========request'log=======");
           e(tag, "method : " + request.method());
           e(tag, "url : " + url);
            if (headers != null && headers.size() > 0) {
               e(tag, "requestHeader's : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                   e(tag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                       e(tag, "requestBody's content : " + bodyToString(request));
                    } else {
                       e(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
           e(tag, "========request'log=======end");
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }


    public void e(String tag, String msg){
        if (isDebug){
            Log.w(tag,msg);
        }
    }
}
