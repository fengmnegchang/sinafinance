package com.open.sina.finance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.open.sina.finance.R;
import com.open.sina.finance.base.MessageHandler;
import com.open.sina.finance.base.activity.CommonTitleBarActivity;

public class SplashActivity extends CommonTitleBarActivity {
    private static final int SHOW_TIME_MIN = 3000;// 最小显示时间
    private long mStartTime;// 开始时间
    private static final int REQUEST_EXTERNAL_STORAGE_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setActivity(this);
        setImplementsDrawerBack(false);
        setExtendsCommonTitleBarActivity(false);
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_splash);
        setExtendsCommonTitleBarActivity(true);
    }

    @Override
    public void initValue() {
        super.initValue();
        mStartTime = System.currentTimeMillis();// 记录开始时间，
        new Thread() {

            /*
             * (non-Javadoc)
             *
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(3000);
                    // UserInfoDBService.userinfo(mContext);
                    weakReferenceHandler.sendEmptyMessage(MessageHandler.MESSAGE_HANDLER);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.run();
            }

        }.start();
    }

    @Override
    public void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        switch (msg.what) {
            case MessageHandler.MESSAGE_HANDLER:// 如果城市列表加载完毕，就发送此消息
                long loadingTime = System.currentTimeMillis() - mStartTime;// 计算一下总共花费的时间
                if (loadingTime < SHOW_TIME_MIN) {// 如果比最小显示时间还短，就延时进入MainActivity，否则直接进入
                    weakReferenceHandler.postDelayed(goToMainActivity, SHOW_TIME_MIN - loadingTime);
                } else {
                    weakReferenceHandler.post(goToMainActivity);
                }
                break;
            default:
                break;
        }
    }

    // 进入下一个Activity
    Runnable goToMainActivity = new Runnable() {
        @Override
        public void run() {
            goToStart();
        }
    };

    /*
 * (non-Javadoc)
 *
 * @see com.open.yaoraotu.viewmodel.SplashViewModel#goToMain()
 */
    public void goToStart() {
        // TODO Auto-generated method stub
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }
}
