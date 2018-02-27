package com.open.sina.finance.activity.news;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.open.sina.finance.R;
import com.open.sina.finance.base.MessageHandler;
import com.open.sina.finance.base.activity.CommonTitleBarActivity;
import com.open.sina.finance.base.adapter.CommonFragmentPagerAdapter;
import com.open.sina.finance.bean.IndicatorBean;
import com.open.sina.finance.callback.JsonGenericsSerializator;
import com.open.sina.finance.callback.SinaStringHandlerGenericsCallback;
import com.open.sina.finance.fragment.news.NewsFragment;
import com.open.sina.finance.json.news.ArticleJson;
import com.open.sina.finance.jsoup.news.ArticleJsoupService;
import com.open.sina.finance.net.NetworkOkHttpManager;
import com.open.sina.finance.net.RequestCollection;
import com.open.sina.finance.utils.LogUtils;
import com.viewpagerindicator.TabPageIndicator;

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

public class NewsTabActivity extends CommonTitleBarActivity {
    public ArrayList<IndicatorBean> list = new ArrayList<IndicatorBean>();
    public ViewPager viewpager;
    public TabPageIndicator indicator;
    public List<String> titleList = new ArrayList<String>();
    public List<Fragment> listRankFragment = new ArrayList<Fragment>();// view数组
    public CommonFragmentPagerAdapter mRankPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setActivity(this);
        setImplementsDrawerBack(false);
        setExtendsCommonTitleBarActivity(false);
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_news_tab,true);
    }


    @Override
    public void initControl() {
        super.initControl();
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
    }

    @Override
    public void initValue() {
        super.initValue();
        mRankPagerAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), listRankFragment, titleList);
        viewpager.setAdapter(mRankPagerAdapter);
        indicator.setViewPager(viewpager);



    }

    @Override
    public void bindEvent() {
        super.bindEvent();

        weakReferenceHandler.sendEmptyMessage(MessageHandler.MESSAGE_HANDLER);
    }

    @Override
    public void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        switch (msg.what){
            case MessageHandler.MESSAGE_HANDLER:
                //请求接口
                LogUtils.d(TAG, "MESSAGE_HANDLER");
                x.task().run(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.add(new IndicatorBean("头条"));
                        list.addAll(ArticleJsoupService.parseIndicator(RequestCollection.WAP, 0));
                        weakReferenceHandler.sendEmptyMessage(MessageHandler.GENERICS_CALL_BACK_RESPONSE0);
                    }
                });
                break;
            case MessageHandler.GENERICS_CALL_BACK_RESPONSE0:
                //正常有数据
                LogUtils.d("RESPONSE");
                titleList.clear();
                listRankFragment.clear();
                for (int i = 0; i < list.size(); i++) {
                    Fragment fragment;
                    titleList.add(list.get(i).inName);
                    if (i == 0) {
                        fragment = NewsFragment.newInstance(false);
                    } else {
                        fragment = NewsFragment.newInstance(false);
                    }
                    listRankFragment.add(fragment);
                }
                mRankPagerAdapter.notifyDataSetChanged();
                indicator.notifyDataSetChanged();
                break;
        }
    }
}
