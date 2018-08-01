package com.qingmang.view.headerFooterView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qingmang.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

/**
 * Created by xiejingbao on 2017/8/12.
 */

public  class ClassicsHeader extends LinearLayout implements RefreshHeader {

    private TextView mHeaderText;//标题文本
    private ImageView mArrowView;//下拉箭头
    private ProgressBar mProgressView;//刷新动画视图
//    private ProgressDrawable mProgressDrawable;//刷新动画
    private AnimationDrawable anim;//刷新动画

    public ClassicsHeader(Context context) {
        super(context);
        initView(context);
    }
    public ClassicsHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }
    public ClassicsHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }
    private void initView(Context context) {
        setGravity(Gravity.CENTER);
        mHeaderText = new TextView(context);
//        mProgressDrawable = new ProgressDrawable();
        mArrowView = new ImageView(context);
        mProgressView = new ProgressBar(context);
        mProgressView.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bga_refresh_loding));
        mArrowView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.refresh_head_arrow));
        anim = (AnimationDrawable) mProgressView.getBackground();
//        mArrowView.parserPaths("M20,12l-1.41,-1.41L13,16.17V4h-2v12.17l-5.58,-5.59L4,12l8,8 8,-8z");
        addView(mProgressView, DensityUtil.dp2px(30), DensityUtil.dp2px(30));
        addView(mArrowView, DensityUtil.dp2px(27), DensityUtil.dp2px(27));
        addView(new View(context), DensityUtil.dp2px(10), DensityUtil.dp2px(10));
        addView(mHeaderText, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        setMinimumHeight(DensityUtil.dp2px(60));

    }
    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }
    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {
//        anim.start();//开始动画
    }
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
//        anim.stop();//停止动画
//        if (success){
//            mHeaderText.setText("刷新完成");
//        } else {
//            mHeaderText.setText("刷新失败");
//        }
        mProgressView.setVisibility(GONE);
        mArrowView.setVisibility(VISIBLE);
        return 500;//延迟500毫秒之后再弹回
    }
    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉刷新");
                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mProgressView.setVisibility(GONE);//隐藏动画
                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                mProgressView.setVisibility(VISIBLE);//显示加载动画
                mArrowView.setVisibility(GONE);//隐藏箭头
                mArrowView.animate().rotation(0);
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放刷新");
                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {
    }
    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }
    @Override
    public void onPullingDown(float percent, int offset, int headHeight, int extendHeight) {
    }
    @Override
    public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {
    }
    @Override
    public void setPrimaryColors(int... colors){
    }
}