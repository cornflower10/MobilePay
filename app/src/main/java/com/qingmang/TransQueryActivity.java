package com.qingmang;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.adapter.QueryTypeAdapter;
import com.qingmang.base.BaseActivity;
import com.qingmang.baselibrary.utils.ReadAssets;
import com.qingmang.moudle.entity.TypeItem;
import com.qingmang.view.CusomBottomSheet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransQueryActivity extends BaseActivity {
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_start)
    TextView tvStart;
    private CusomBottomSheet cusomBottomSheet;
    private List<TypeItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_query);
        ButterKnife.bind(this);
        String str = "[]";
        try {
            str = ReadAssets.readAssetsTxt(this, "type.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        list = JSONObject.parseArray(str, TypeItem.class);

        cusomBottomSheet = new CusomBottomSheet(mContext);
        View workStatusDialogView = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet, null);
        RecyclerView recyclerView = (RecyclerView) workStatusDialogView.findViewById(R.id.rv_bottom);

        QueryTypeAdapter stringAdapter = new QueryTypeAdapter(list);
        stringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list.get(position).setCheck(true);
                for (TypeItem typeItem :
                        list) {
                    if (list.get(position).getId() != typeItem.getId()) {
                        typeItem.setCheck(true);
                    }
                }
                cusomBottomSheet.dismissSheet();
                tvType.setText(list.get(position).getName());
            }
        });
        recyclerView.setAdapter(stringAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        cusomBottomSheet.setContentView(workStatusDialogView);
//        cusomBottomSheet.show();
        initTimePicker();
    }


    @OnClick({R.id.tv_start, R.id.tv_end_date, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                timePickerView.show(tvStart);
                break;
            case R.id.tv_end_date:
                timePickerView.show(tvEndDate);
                break;
            case R.id.tv_type:
                cusomBottomSheet.showSheet();
                break;
        }
    }


//
//    private void initDatePicker() {
//        DatePicker  datePicker = new DatePicker(this);
//        datePicker.setRange(1900, 2100);
//        datePicker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
//            @Override
//            public void onDatePicked(String year, String month, String day) {
//                tvIdDate.setText(year + "-" + month + "-" + day);
//            }
//        });
//    }


    private TimePickerView timePickerView;

    private void initTimePicker() {//Dialog 模式下，在底部弹出
       Calendar calendarStart = Calendar.getInstance();
       calendarStart.set(2010,1,1);
        Calendar calendarEnd = Calendar.getInstance();


        timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(mContext, getTime(date), Toast.LENGTH_SHORT).show();
                if (v.equals(tvEndDate)){
                    tvEndDate.setText(getTime(date));
                }else {
                    tvStart.setText(getTime(date));
                }


            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
               .setRangDate(null,calendarEnd)
                .isDialog(true)
                .build();

        Dialog mDialog = timePickerView.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            timePickerView.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
