package com.qingmang.base;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.uilibrary.loadview.LoadViewHelper;

import butterknife.ButterKnife;


/**
 * Created by xiejingbao on 2018/1/9.
 */

public abstract class BaseMvpActivity<P extends Presenter<V>,V extends BaseView> extends BaseActivity implements BaseView{
   public P presenter;
    private final int BASE_LODER_ID = 1000;//loader的id值
    public LoadViewHelper loadViewHelper;
    private boolean showBack = true;
    public abstract String setTitleName();
    public abstract View getRootView();
    public abstract int setContentView();
    public abstract void initViewAndData();
    protected abstract P initPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        ButterKnife.bind(this);
        presenter = initPresenter();
        LogManager.i("-----BaseMvpActivity-------");
        initToolar();
        initViewAndData();
        if(null!=getRootView())
            loadViewHelper = new LoadViewHelper(getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogManager.i("-----onStart-------");
        presenter.attachView((V)this);

    }



    @Override
    public void onError(String msg) {
        showToast(msg);

    }

    private void initToolar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(null!=toolbar){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            if(showBack){
                toolbar.setNavigationIcon(R.drawable.icon_left);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
            TextView textView = (TextView) findViewById(R.id.title_name);
            textView.setText(setTitleName());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        presenter.detachView();
        presenter = null;
        super.onDestroy();
    }

}
