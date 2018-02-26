package com.open.sina.finance.base.fragment;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.open.sina.finance.R;
import com.open.sina.finance.base.MessageHandler;
import com.open.sina.finance.refresh.OnHandleRefreshListener;
import com.open.sina.finance.utils.LogUtils;
import com.open.sina.finance.utils.ToastUtils;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/16.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public abstract class CommonRefreshFragment<T extends PullToRefreshBase<V>, V extends View,F extends BaseFragment> extends BaseFragment<F>
        implements OnHandleRefreshListener<T, V>, PullToRefreshBase.OnRefreshListener2<V>, PullToRefreshBase.OnPullEventListener<V> {
    private T t;//刷新控件
    private boolean isFirstLoad = true;
    protected boolean isRequestingButNoutResult;
    protected TextView txtToast;
    private boolean isSwitchViewData;

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            if (weakReferenceHandler!=null){
                weakReferenceHandler.removeCallbacksAndMessages(null);
                weakReferenceHandler = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void setRefreshing() {
        if (t != null && weakReferenceHandler != null) {
            weakReferenceHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    t.setRefreshing();
                }
            }, 100);
        }
    }

    @Override
    public void onStopRefresh() {
        LogUtils.d(TAG, "onStopRefresh");
        if (weakReferenceHandler != null) {
//            weakReferenceHandler.sendEmptyMessage(MessageHandler.REFRESH_COMPLETE3);
            if (t != null) {
                // SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-HH:mm:ss");
                // Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
                // String time = formatter.format(curDate);
                // if (t.getCurrentMode() == Mode.PULL_FROM_START) {
                // pullFromStartLastTime = time;
                // } else if (t.getCurrentMode() == Mode.PULL_FROM_END) {
                // pullFromEndLastTime = time;
                // }
                t.getLoadingLayoutProxy().setLastUpdatedLabel(t.getContext().getResources().getString(R.string.refreshing_waiting));
                t.onRefreshComplete();
            }
        }
    }


    @Override
    public boolean isPullDownRefresh() {
        return (t != null ? t.getCurrentMode() : PullToRefreshBase.Mode.PULL_FROM_START) == PullToRefreshBase.Mode.PULL_FROM_START;// onLoadRefreshView == null ? true : onLoadRefreshView.isPullDownRefresh();
    }

    @Override
    public boolean isFirstLoad() {
        return isFirstLoad;// onLoadRefreshView == null ? true : onLoadRefreshView.isFirstLoad();
    }

    @Override
    public void setIsFirstLoad(boolean isFirstLoad) {
        this.isFirstLoad = isFirstLoad;
        // if (onLoadRefreshView != null) {
        // onLoadRefreshView.setIsFirstLoad(isFirstLoad);
        // }
    }


    @Override
    public T getPullView() {
        return t;// onLoadRefreshView != null ? onLoadRefreshView.getPullView() : null;
    }

    @Override
    public V getRefreshView() {
        return t != null ? t.getRefreshableView() : null;
    }

    @Override
    public void setRefreshMode(PullToRefreshBase.Mode mode) {
        if (t != null) {
            t.setMode(mode);
        }
    }


    public boolean isSwitchData() {
        return isSwitchViewData;// onLoadRefreshView != null ? onLoadRefreshView.isSwitchData() : false;
    }

    public void setIsSwitchViewData(boolean isSwitchViewData) {
        this.isSwitchViewData = isSwitchViewData;
        // if (onLoadRefreshView != null) {
        // onLoadRefreshView.setIsSwitchViewData(isSwitchViewData);
        // }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
        initUI(isVisibleToUser);
    }

    protected void initUI(final boolean isVisibleToUser) {
        if (weakReferenceHandler != null) {
            weakReferenceHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getActivity() == null || !isVisibleToUser) {
                        initUI(isVisibleToUser);
                    } else {
                        if(isFirstLoad){
                            weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.QUERY_CACHE_FROM_LOCAL, 50);
                            setIsFirstLoad(false);
                        }
//                        else{
//                            weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.MESSAGE_DEFAULT_POSITION, 50);
//                        }

                    }
                }
            }, 200);
        }
    }




    protected void setRefreshView(T t) {
        // if (onLoadRefreshView == null) {
        // onLoadRefreshView = ImplOnLoadRefreshView.LoadRefreshView(t, this);
        // }
        this.t = t;
    }


    @Override
    public void onPullUpToRefresh(PullToRefreshBase<V> refreshView) {
        if (refreshView != null) {
             if (weakReferenceHandler!=null){
                 weakReferenceHandler.sendEmptyMessage(MessageHandler.REFRESHING_VIEW);
             }
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<V> refreshView) {
        if (refreshView != null)
            if (weakReferenceHandler!=null){
                weakReferenceHandler.sendEmptyMessage(MessageHandler.REFRESHING_VIEW);
            }
    }

    @SuppressLint("Recycle")
    @Override
    public void onPullEvent(PullToRefreshBase<V> refreshView, PullToRefreshBase.State state, PullToRefreshBase.Mode direction) {

    }

    @Override
    public void handlerMessage(Message msg) {
        super.handlerMessage(msg);
        try {
            if (msg!=null){
                switch (msg.what){
                    case MessageHandler.REFRESHING_VIEW:
                        LogUtils.e(TAG,"===========REFRESHING_VIEW===========");
                        if (t != null) {
                            if (!isRequestingButNoutResult) {
                                isRequestingButNoutResult = true;
                                if (t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
                                    weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_PULL_DOWN, 100);
                                } else if (t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
                                    weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_PULL_UP, 100);
                                }
                                weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_ERROR,1000);
                            } else {
                                t.getLoadingLayoutProxy().setLastUpdatedLabel(t.getContext().getResources().getString(R.string.refreshing_waiting));
                                t.onRefreshComplete();
                            }
                        }
                        break;
                    case MessageHandler.REFRESH_COMPLETE:
                        LogUtils.e(TAG,"===========REFRESH_COMPLETE===========");
                        if (t != null && t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {// 下拉刷新
                            if (isFirstLoad()) {
                                ToastUtils.showShort("亲,木有获取到数据");
                            } else {
                                ToastUtils.showShort("亲,木有新的数据了");
                            }
                        } else if (t != null && t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {// 上拉加载更多
                            ToastUtils.showShort("亲,木有更多数据了");
                        }
                        onStopRefresh();
                        break;
                    case MessageHandler.REFRESH_ERROR:
                        LogUtils.e(TAG,"===========REFRESH_ERROR===========");
                        onStopRefresh();
                        break;
                    case MessageHandler.REFRESH_COMPLETE3:
                        LogUtils.e(TAG,"===========REFRESH_COMPLETE3===========");
                        isRequestingButNoutResult = false;
                        break;
                    default:
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    //    public void refreshview(){
//        if (t != null ) {
//            if (!isRequestingButNoutResult && weakReferenceHandler!=null) {
//                isRequestingButNoutResult = true;
//                if (t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_START) {
//                    weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_PULL_DOWN, 300);
//                } else if (t.getCurrentMode() == PullToRefreshBase.Mode.PULL_FROM_END) {
//                    weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_PULL_UP, 300);
//                }
//                weakReferenceHandler.sendEmptyMessageDelayed(MessageHandler.REFRESH_ERROR, 300);
//            } else {
//                t.getLoadingLayoutProxy().setLastUpdatedLabel(t.getContext().getResources().getString(R.string.refreshing_waiting));
//                t.onRefreshComplete();
//                isRequestingButNoutResult = false;
//            }
//        }
//    }

    /**
     * 设置toast提示内容
     */
    protected void setToastText(String strToastContent) {
        if (txtToast != null) {
            txtToast.setText(strToastContent);
            txtToast.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏ToastText
     */
    protected void setToastViewGone() {
        if (txtToast != null) {
            txtToast.setVisibility(View.GONE);
        }
    }

    /**
     * 设置txtToast
     */
    protected void setTextView(TextView textView) {
        txtToast = textView;
    }


    /***
     * 设置刷新 字体颜色
     * @param color
     */
    public void setLoadingLayoutTextColor(int color){
        getPullView().getHeaderLayout().setTextColor(ColorStateList.valueOf(getResources().getColor(color)));
        getPullView().getFooterLayout().setTextColor(ColorStateList.valueOf(getResources().getColor(color)));
    }


}
