/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-11-2上午11:40:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-11-2上午11:40:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
public class PullToRefreshLinearLayout extends PullToRefreshBase<LinearLayout> {
	public PullToRefreshLinearLayout(Context context) {
		super(context);
		setSingleView(true);
	}

	public PullToRefreshLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSingleView(true);
	}

	public PullToRefreshLinearLayout(Context context, Mode mode) {
		super(context, mode);
		setSingleView(true);
	}

	public PullToRefreshLinearLayout(Context context, Mode mode, AnimationStyle animStyle) {
		super(context, mode, animStyle);
		setSingleView(true);
	}

	@Override
	public Orientation getPullToRefreshScrollDirection() {
		return Orientation.VERTICAL;
	}

	@Override
	protected LinearLayout createRefreshableView(Context context, AttributeSet attrs) {
		return new LinearLayout(context, attrs);
	}

	@Override
	protected boolean isReadyForPullEnd() {
		return true;
	}

	@Override
	protected boolean isReadyForPullStart() {
		return true;
	}
}