package com.qingmang.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qingmang.R;
import com.qingmang.moudle.entity.TypeItem;

import java.util.List;

/**
 * Created by xiejingbao on 2018/3/9.
 */

public class QueryTypeAdapter extends BaseQuickAdapter<TypeItem, BaseViewHolder> {


    public QueryTypeAdapter(@Nullable List<TypeItem> data) {
        super(R.layout.string_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeItem item) {
       TextView textView = helper.getView(R.id.tv_name);
       helper.setText(R.id.tv_name,item.getName());
      if(item.isCheck()){
          textView.setTextColor(ContextCompat.getColor(mContext,R.color.main_blue));
      }else {
          textView.setTextColor(ContextCompat.getColor(mContext,R.color.text_333333));

      }
    }


}
