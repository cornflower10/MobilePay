package com.qingmang;

import android.view.View;

import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonPresenter;
import com.qingmang.base.CommonView;

public class MyActivity extends BaseMvpActivity<CommonPresenter,CommonView> implements CommonView {

    @Override
    public String setTitleName() {
        return "我的";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_my;
    }

    @Override
    public void initViewAndData() {

    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }


    @Override
    public void onDataSuccess(Object o) {

    }
}
