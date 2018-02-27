package com.open.sina.finance.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.open.sina.finance.R;
import com.open.sina.finance.base.activity.BaseTabActivity;
import com.open.sina.finance.bean.MainTabBean;
import com.open.sina.finance.utils.ActivityUtils;

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

public class MainTabActivity extends BaseTabActivity {

    private TabHost mTabHost;
    private RadioGroup mRadioGroup;
    private List<MainTabBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 透明导航栏
        //        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        init();


    }

    @Override
    protected void findView() {
        super.findView();
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mTabHost = getTabHost();
    }


    @Override
    protected void initValue() {
        super.initValue();
        list.clear();
        list.add(new MainTabBean(R.drawable.selector_tab_news, getResources().getString(R.string.tab_main_news)));
        list.add(new MainTabBean(R.drawable.selector_tab_market, getResources().getString(R.string.tab_main_market)));
        list.add(new MainTabBean(R.drawable.selector_tab_mystock, getResources().getString(R.string.tab_main_mystock)));
        list.add(new MainTabBean(R.drawable.selector_tab_live, getResources().getString(R.string.tab_main_live)));
        list.add(new MainTabBean(R.drawable.selector_tab_me, getResources().getString(R.string.tab_main_me)));

        for (int i = 0; i < list.size(); i++) {
            MainTabBean mbean = list.get(i);
            TabHost.TabSpec tab_main = mTabHost.newTabSpec(mbean.tabName);
            Intent intent = null;
            if (mbean.tabName.equals(getResources().getString(R.string.tab_main_news))) {
                intent = new Intent(this, GuideActivity.class);
            } else if (mbean.tabName.equals(getResources().getString(R.string.tab_main_market))) {
                intent = new Intent(this, GuideActivity.class);
            } else if (mbean.tabName.equals(getResources().getString(R.string.tab_main_mystock))) {
                intent = new Intent(this, GuideActivity.class);
            } else if (mbean.tabName.equals(getResources().getString(R.string.tab_main_live))) {
                intent = new Intent(this, GuideActivity.class);
            }else if (mbean.tabName.equals(getResources().getString(R.string.tab_main_me))) {
                intent = new Intent(this, GuideActivity.class);
            }
            tab_main.setContent(intent).setIndicator(mbean.tabName);
            mTabHost.addTab(tab_main);
            mTabHost.setCurrentTab(0);

        }
    }


    @Override
    protected void bindEvent() {
        super.bindEvent();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.radio_news:
                        mTabHost.setCurrentTab(0);
                        break;
                    case R.id.radio_market:
                        mTabHost.setCurrentTab(1);
                        break;
                    case R.id.radio_mystock:
                        mTabHost.setCurrentTab(2);
                        break;
                    case R.id.radio_live:
                        mTabHost.setCurrentTab(3);
                        break;
                    case R.id.radio_me:
                        mTabHost.setCurrentTab(4);
                        break;

                }
            }
        });

//        new UpdateAppManager
//                        .Builder()
//                        //当前Activity
//                        .setActivity(this)
//                        //更新地址
//                        .setUpdateUrl(RequestCollection.UPDATE_URL)
//                        //实现httpManager接口的对象
//                        .setHttpManager(new UpdateAppHttpUtil())
//                        .build()
//                        .update();

        //Start polling service
//        LogUtils.d(TAG,"Start polling service...");
//        int seconds= 10000;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
//            seconds = 100;
//        }else {
//            seconds = 10000;
//        }
//        PollingUtils.startPollingService(this, seconds, PollingUnReadMsgService.class, PollingUnReadMsgService.ACTION);ACTION

    }



    @Override
    public void onBackPressed() {
        ActivityUtils.startHomeActivity();
    }


    /**
     *
     * @param position
     */
    public void setCurrentTab(int position){
        try {
            switch (position){
                case 0:
                    ((RadioButton) findViewById(R.id.radio_news)).setChecked(true);
                    break;
                case 1:
                    ((RadioButton) findViewById(R.id.radio_market)).setChecked(true);
                    break;
                case 2:
                    ((RadioButton) findViewById(R.id.radio_mystock)).setChecked(true);
                    break;
                case 3:
                    ((RadioButton) findViewById(R.id.radio_live)).setChecked(true);
                    break;
                case 4:
                    ((RadioButton) findViewById(R.id.radio_me)).setChecked(true);
                    break;
            }
            mTabHost.setCurrentTab(position);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Stop polling service
//        LogUtils.d(TAG,"Stop polling service...");
//        PollingUtils.stopPollingService(this, PollingUnReadMsgService.class, PollingUnReadMsgService.ACTION);
    }

    public static void startMainTabActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainTabActivity.class);
        context.startActivity(intent);
    }

}

