package com.open.sina.finance.fragment.news;

import android.os.Message;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.sina.finance.adapter.news.NewsAdapter;
import com.open.sina.finance.base.MessageHandler;
import com.open.sina.finance.base.fragment.CommonRefreshListViewFragment;
import com.open.sina.finance.bean.news.ArticleBean;
import com.open.sina.finance.callback.JsonGenericsSerializator;
import com.open.sina.finance.callback.SinaStringHandlerGenericsCallback;
import com.open.sina.finance.db.LocalCacheManager;
import com.open.sina.finance.db.SqlEntry;
import com.open.sina.finance.json.news.ArticleJson;
import com.open.sina.finance.net.NetworkOkHttpManager;
import com.open.sina.finance.net.RequestBuilderID;
import com.open.sina.finance.net.RequestCollection;
import com.open.sina.finance.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.IGenericsSerializator;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/27.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class NewsFragment extends CommonRefreshListViewFragment {
    private NewsAdapter mNewsAdapter;
    private List<ArticleBean> list = new ArrayList<>();


    public static NewsFragment newInstance(boolean isVisibleToUser) {
        NewsFragment fragment = new NewsFragment();
        fragment.setFragment(fragment);
        fragment.setUserVisibleHint(isVisibleToUser);
        return fragment;
    }


    @Override
    public void initValue() {
        super.initValue();
        mNewsAdapter = new NewsAdapter(list, getActivity());
        setAdapter(mNewsAdapter);
        setRefreshMode(PullToRefreshBase.Mode.BOTH);
        weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.QUERY_CACHE_FROM_LOCAL, 300);
    }


    @Override
    public void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        switch (msg.what) {
            case MessageHandler.QUERY_CACHE_FROM_LOCAL:
                //查询本地缓存
                LogUtils.d(TAG, "QUERY_CACHE_FROM_LOCAL");
                x.task().run(new Runnable() {
                    @Override
                    public void run() {
                        ArticleJson cache = null;
                        cache = LocalCacheManager.queryCache(TAG + RequestCollection.NEWS_CALLBACK  + pageStart, SqlEntry.COLUMN_CACHE_TYPE_OFFLINE_JSON, ArticleJson.class);
                        weakReferenceHandler.sendMessage(weakReferenceHandler.obtainMessage(MessageHandler.QUERY_CACHE_COMPLETE, cache));
                    }
                });
                break;
            case MessageHandler.QUERY_CACHE_COMPLETE:
                //刷新缓存数据
                LogUtils.d(TAG, "QUERY_CACHE_COMPLETE");
                ArticleJson cache = (ArticleJson) msg.obj;
                if (cache != null) {
                    list.clear();
                    list.addAll(cache.getData());
                    mNewsAdapter.notifyDataSetChanged();
                }
                setRefreshing();
                break;
            case MessageHandler.REFRESH_PULL_DOWN:
                //下拉刷新
                LogUtils.d(TAG, "REFRESH_PULL_DOWN");
                pageStart = 0;
                weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.MESSAGE_HANDLER, 100);
                break;
            case MessageHandler.REFRESH_PULL_UP:
                //上拉刷新，加载分页
                LogUtils.d(TAG, "REFRESH_PULL_UP");
                pageStart++;
                weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.MESSAGE_HANDLER, 100);
                break;
            case MessageHandler.MESSAGE_HANDLER:
                //请求接口
                LogUtils.d(TAG, "MESSAGE_HANDLER");
//                x.task().run(new Runnable() {
//                    @Override
//                    public void run() {
//                        ArticleJson cache2 = new ArticleJson();
//                        cache2.setData(ArticleJsoupService.parseArticle(RequestCollection.WAP, pageStart));
//                        weakReferenceHandler.sendMessage(weakReferenceHandler.obtainMessage(MessageHandler.GENERICS_CALL_BACK_RESPONSE0, pageStart, pageStart, cache2));
//                    }
//                });
                NetworkOkHttpManager.newsWap(this, new SinaStringHandlerGenericsCallback<String>(getActivity(),new JsonGenericsSerializator(),weakReferenceHandler){});
                break;
            case MessageHandler.GENERICS_CALL_BACK_ERROR:
                //异常
                LogUtils.d("ERROR");
                break;
            case MessageHandler.GENERICS_CALL_BACK_RESPONSE0:
                //正常有数据
                LogUtils.d("RESPONSE");
                weakReferenceHandler.sendEmptyMessage(MessageHandler.REFRESH_COMPLETE3);
                if (msg.obj != null) {
                    String response = (String) msg.obj;
                    response = response.replace("\n","").split("\\(\\{")[1];
                    response =  "{"+response.replace(");","");//replace("jQuery21401875673967376692_1519718544706(","");

                    Gson gson = new Gson();
                    ArticleJson articleJson = gson.fromJson(response,ArticleJson.class);
                    if (getPullView().getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                        list.clear();
                        list.addAll(articleJson.getData());
                        mNewsAdapter.notifyDataSetChanged();
                        setToastViewGone();
                        LocalCacheManager.saveToCache(response, TAG + RequestCollection.NEWS_CALLBACK + pageStart, SqlEntry.COLUMN_CACHE_TYPE_OFFLINE_JSON);
                    } else if (getPullView().getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                        list.addAll(articleJson.getData());
                        mNewsAdapter.notifyDataSetChanged();
                        setToastViewGone();
                    }
                }
                break;
            case MessageHandler.GENERICS_CALL_BACK_RESPONSE1:
                //正常有数据
                LogUtils.d(TAG, "GENERICS_CALL_BACK_RESPONSE1");
                break;
            case MessageHandler.REFRESH_COMPLETE3:
                LogUtils.d(TAG, "REFRESH_COMPLETE3");
                onStopRefresh();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
