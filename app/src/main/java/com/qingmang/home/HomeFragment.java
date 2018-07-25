package com.qingmang.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.ConsumeActivity;
import com.qingmang.MyActivity;
import com.qingmang.R;
import com.qingmang.adapter.CheckBoxAdapter;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.base.CommonPresenter;
import com.qingmang.base.CommonView;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiejingbao on 2017/9/14.
 */

public class HomeFragment extends BaseMvpFragment<CommonPresenter, CommonView> implements CommonView {
    @BindView(R.id.home_banner)
    BannerLayout homeBanner;
    @BindView(R.id.rv)
    RecyclerView rv;
    private static final String[] name = {"消费", "撤销", "退款", "交易查询", "报表统计"};
    String URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532368316048&di=c5e0b6982f0d68c81d9a47b958859c5e&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb64543a98226cffceee78e5eb5014a90f703ea09.jpg";

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        List<String> urls = new ArrayList<>();
        urls.add(URL);
        urls.add(URL);
        urls.add(URL);
        urls.add(URL);
        urls.add(URL);
        homeBanner.setViewUrls(urls);
        homeBanner.startAutoPlay();
        CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(Arrays.asList(name));
        rv.setAdapter(checkBoxAdapter);
        checkBoxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    startActivity(ConsumeActivity.class);
                }
            }
        });
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected CommonPresenter createPresenter() {
        return new CommonPresenter();
    }

    @Override
    public void onDataSuccess(Object o) {

    }

    @Override
    public void onError(String msg) {

    }


    @OnClick(R.id.title_rightIv)
    public void onViewClicked() {
        startActivity(MyActivity.class);
    }
}
