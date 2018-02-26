package com.open.sina.finance.db;

import com.google.gson.Gson;
import com.open.sina.finance.base.CommonApplication;
import com.open.sina.finance.utils.LogUtils;
import com.open.sina.finance.utils.SPUtils;

import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

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

public class LocalCacheManager {


    /****
     * 保存cache
     * @param t
     * @param whichPage
     * @param cachetype
     * @param <T>
     */
    public static <T> void saveToCache(T t,final String whichPage,final String cachetype) {
        try {
            if (t != null) {
                Gson gson = new Gson();
                String json = gson.toJson(t);
                final SqlEntry entry = new SqlEntry();
                entry.setJson(json);
                entry.setWhichpage(whichPage);
                entry.setCachetype(cachetype);
                entry.setUserid(SPUtils.getLoginUserId());
                LogUtils.d("saveToCache","whichPage=="+whichPage+";json="+json);
                x.task().run(new Runnable() {
                    @Override
                    public void run() {
                        //先判断是否存在，有更新；无插入
                        try {
                            x.getDb(CommonApplication.getInstance().getDaoConfig()).delete(SqlEntry.class,
                                    WhereBuilder.b(SqlEntry.COLUMN_WHICH_PAGE, "=", whichPage).and(SqlEntry.COLUMN_CACHE_TYPE, "=", cachetype).and(SqlEntry.COLUMN_USER_ID,"=",SPUtils.getLoginUserId()));
                            x.getDb(CommonApplication.getInstance().getDaoConfig()).saveOrUpdate(entry);
                        } catch (DbException ee) {
                            ee.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 清除缓存
     * @param whichPage
     * @param cachetype
     */
    public static void removeCache(final String whichPage, final String cachetype) {
        try {
            Gson gson = new Gson();
            final SqlEntry entry = new SqlEntry();
            entry.setWhichpage(whichPage);
            entry.setCachetype(cachetype);
            entry.setUserid(SPUtils.getLoginUserId());
            x.task().run(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogUtils.d("dropCache","whichPage=="+whichPage+";userid=="+SPUtils.getLoginUserId());
                        x.getDb(CommonApplication.getInstance().getDaoConfig()).delete(SqlEntry.class,
                                WhereBuilder.b(SqlEntry.COLUMN_WHICH_PAGE, "=", whichPage).and(SqlEntry.COLUMN_CACHE_TYPE, "=", cachetype).and(SqlEntry.COLUMN_USER_ID,"=",SPUtils.getLoginUserId()));
                    } catch (DbException ee) {
                        ee.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 清除用户缓存
     * @param userId
     * @param cachetype
     */
    public static void clearUserCache(final String userId,final String cachetype) {
        try {
            Gson gson = new Gson();
            final SqlEntry entry = new SqlEntry();
            entry.setCachetype(cachetype);
            entry.setUserid(userId);
            x.task().run(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogUtils.d("dropCache","userId=="+userId);
                        x.getDb(CommonApplication.getInstance().getDaoConfig()).delete(SqlEntry.class,
                                WhereBuilder.b(SqlEntry.COLUMN_USER_ID, "=", userId).and(SqlEntry.COLUMN_CACHE_TYPE, "=", cachetype));
                    } catch (DbException ee) {
                        ee.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 清空 表数据
     * @param cachetype
     */
    public static void dropCache(final String cachetype) {
        try {
            Gson gson = new Gson();
            final SqlEntry entry = new SqlEntry();
            entry.setCachetype(cachetype);
            x.task().run(new Runnable() {
                @Override
                public void run() {
                    try {
                        LogUtils.d("dropCache","cachetype=="+cachetype);
                        x.getDb(CommonApplication.getInstance().getDaoConfig()).delete(SqlEntry.class,
                                WhereBuilder.b(SqlEntry.COLUMN_CACHE_TYPE, "=", cachetype));
                    } catch (DbException ee) {
                        ee.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /****
     * 取本地缓存
     * @param whichPage TAG + URL + PAGE
     * @param cachetype
     * @param typeOfT
     * @param <T>
     * @return
     */
    public static <T> T queryCache(final String whichPage, final String cachetype, final Type typeOfT) {
        T t = null;
        try {
            List<SqlEntry> list = x.getDb(CommonApplication.getInstance().getDaoConfig()).selector(SqlEntry.class).
                    where(WhereBuilder.b(SqlEntry.COLUMN_WHICH_PAGE, "=", whichPage).and(SqlEntry.COLUMN_CACHE_TYPE, "=", cachetype).and(SqlEntry.COLUMN_USER_ID,"=",SPUtils.getLoginUserId())).findAll();
            if (list != null && list.size() > 0) {
                String json = list.get(0).getJson();
                LogUtils.d("queryCache","whichPage=="+whichPage+";json="+json);
                if (json != null && json.length() > 0) {
                    Gson gson = new Gson();
                    t = gson.fromJson(json, typeOfT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
