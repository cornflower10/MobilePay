package com.qingmang;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.qingmang.base.BaseActivity;
import com.qingmang.baselibrary.utils.ReadAssets;
import com.qingmang.moudle.entity.ItemIncomeValue;
import com.qingmang.view.IncomeBarChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportActivity extends BaseActivity {

    @BindView(R.id.ibBar)
    IncomeBarChart ibBar;
    private String data = "[]";
    private List<ItemIncomeValue> dateValueList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        try {
            data = ReadAssets.readAssetsTxt(this, "bar_chart.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dateValueList = JSONObject.parseArray(data, ItemIncomeValue.class);
        showBarChart(dateValueList,"收款柱状图", ContextCompat.getColor(mContext,R.color.main_blue));
    }

    /**
     * 柱状图始化设置 一个BarDataSet 代表一列柱状图
     *
     * @param barDataSet 柱状图
     * @param color      柱状图颜色
     */
    private void initBarDataSet(BarDataSet barDataSet, int color) {
        barDataSet.setColor(color);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(15.f);
        //不显示柱状图顶部值
        barDataSet.setDrawValues(false);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.5f);//柱状图宽度
//        barDataSet.setValueTextSize(10f);
//        barDataSet.setValueTextColor(color);

    }
    public void showBarChart(final List<ItemIncomeValue> dateValueList, String name, int color) {



        //X轴自定义值
        ibBar.getXAxis().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dateValueList.get((int) value % dateValueList.size()).getDate();
            }
        });

        //右侧Y轴自定义值
        ibBar.getAxisLeft().setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (value) + "元";
            }
        });
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < dateValueList.size(); i++) {
            /**
             * 此处还可传入Drawable对象 BarEntry(float x, float y, Drawable icon)
             * 即可设置柱状图顶部的 icon展示
             */
            BarEntry barEntry = new BarEntry(i, (float) dateValueList.get(i).getAmount());
            entries.add(barEntry);
        }
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, name);
        initBarDataSet(barDataSet, color);

//        // 添加多个BarDataSet时
//        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//        BarData data = new BarData(dataSets);

        BarData data = new BarData(barDataSet);
        ibBar.setData(data);
    }


}
