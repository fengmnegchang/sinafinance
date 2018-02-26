package com.open.sina.finance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.open.sina.finance.R;
import com.open.sina.finance.base.fragment.BaseFragment;


/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/1/15.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class CommonV4Fragment extends BaseFragment {

    public static CommonV4Fragment newInstance(boolean isVisibleToUser) {
        CommonV4Fragment fragment = new CommonV4Fragment();
        fragment.setFragment(fragment);
        fragment.isVisibleToUser = isVisibleToUser;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_v4, container, false);
    }

}
