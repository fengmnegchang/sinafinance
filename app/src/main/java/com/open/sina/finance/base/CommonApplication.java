package com.open.sina.finance.base;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.open.sina.finance.BuildConfig;
import com.open.sina.finance.utils.ActivityUtils;
import com.open.sina.finance.utils.CrashUtils;
import com.open.sina.finance.utils.LogUtils;
import com.open.sina.finance.utils.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * ****************************************************************************************************************************************************************************
 * 上线修改 1. LogUtils.getConfig().setLogSwitch(BuildConfig.DEBUG);
 * 2. x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
 * 3. RequestCollection.setIsDebug(BuildConfig.DEBUG);
 * 4..addInterceptor(new LoggerInterceptor(BuildConfig.DEBUG))
 * @author :fgj
 * @createTime: 2018/1/11.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class CommonApplication extends Application {
    private static CommonApplication instance;

    public static CommonApplication getInstance() {
        return instance;
    }

    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 将MultiDex注入到项目中
        MultiDex.install(this);
        instance = this;
        //网络
        //初始化工具类
        Utils.init(this);

        // 内存泄露检查工具
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

        //是否打印Log
        final LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(BuildConfig.DEBUG)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(false)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(false)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogUtils.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1);// log 栈深度，默认为 1
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtils.d(config.toString());
            }
        }).start();


        try {
            //网络
//        ClearableCookieJar cookieJar1 = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));
//            InputStream  cerStream = this.getAssets().open("ROOT-ca.crt");
//            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{cerStream}, this.getAssets().open("otc_android.bks"), "123456");
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null,null);
            //        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    //                .cache(new Cache(new File(getExternalCacheDir().toString(),"okhttpcache"), 10 * 1024 * 1024))
                    //日志管理
                    .addInterceptor(new LoggerInterceptor(BuildConfig.DEBUG))
                    //                .cookieJar(cookieJar1)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        }catch (Exception e){
            e.printStackTrace();
        }

        //图片
        ImageLoaderConfiguration imgconfig = new ImageLoaderConfiguration.Builder(this).threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release app
                //                .imageDownloader(new AuthImageDownloader(context))
                .build();
        ImageLoader.getInstance().init(imgconfig);

        //二维码初始化
        //ZXingLibrary.initDisplayOpinion(this);

        //设置xutils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能
        daoConfig = new DbManager.DaoConfig()
                .setDbName("sljr_otc_db")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作

        //接口环境
        //RequestCollection.setIsDebug(BuildConfig.DEBUG);

        // 开启崩溃日志，这样我们的程序就能捕获未处理的异常
        CrashUtils.init(new CrashUtils.OnCrashListener() {
            @Override
            public void onCrash(Throwable e) {
                e.printStackTrace();
                restartApp();
            }
        });
    }

    private void restartApp() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), getPackageName()+".activity.MainTabActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent restartIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        if (manager == null) return;
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 1, restartIntent);
        ActivityUtils.finishAllActivities();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
