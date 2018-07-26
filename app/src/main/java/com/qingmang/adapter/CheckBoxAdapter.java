package com.qingmang.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.moudle.entity.Item;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class CheckBoxAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {


    public CheckBoxAdapter(@Nullable List<Item> data) {
        super(R.layout.util_box_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
       helper.setText(R.id.tv_name,item.getName());
       helper.setImageResource(R.id.iv_icon,item.getRes());
    }


}
