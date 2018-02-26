package com.vector.update_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :fgj
 * @createTime: 2018/2/8.
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class UpdateDialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app_dialog);

        Bundle bundle = getIntent().getExtras();

        UpdateDialogFragment updateDialogFragment = new UpdateDialogFragment();
        updateDialogFragment.setArguments(bundle);
        updateDialogFragment.show(getSupportFragmentManager(), "dialog");
    }


    public static void startUpdateDialogActivity(Context context, Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(context,UpdateDialogActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
