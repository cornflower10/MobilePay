package com.qingmang.baselibrary.utils.datepicker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class TextPicker extends WheelPicker {

    private ArrayList<String> years = new ArrayList<String>();
    private OnDatePickListener onDatePickListener;
    private String yearLabel = "";
    private int selectedYearIndex = 0;
    private Mode mode = Mode.YEAR;
//    private int endDay;

    /**
     * The enum Mode.
     */
    public enum Mode {
        YEAR,
        /**
         *
         */
        TEXT
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     */
    public TextPicker(Activity activity) {
        this(activity, Mode.YEAR);
    }

    /**
     * Instantiates a new Date picker.
     *
     * @param activity the activity
     * @param mode     the mode
     */
    public TextPicker(Activity activity, Mode mode) {
        super(activity);
        this.mode = mode;
        for (int i = 2000; i <= 2050; i++) {
            years.add(String.valueOf(i));
        }

    }

    public TextPicker(Activity activity, String [] strs,Mode mode) {
        super(activity);
        if(mode.equals(Mode.TEXT)){
            years.addAll(Arrays.asList(strs));
        }else {
            for (int i = 2000; i <= 2050; i++) {
                years.add(String.valueOf(i));
            }
        }


    }

    /**
     * Sets label.
     *
     * @param yearLabel  the year label
     *
     */
    public void setLabel(String yearLabel) {
        this.yearLabel = yearLabel;

    }

    /**
     * Sets range.
     *
     * @param startYear the start year
     * @param endYear   the end year
     */
    public void setRange(int startYear, int endYear) {
        years.clear();
        for (int i = startYear; i <= endYear; i++) {
            years.add(String.valueOf(i));
        }
    }


    private int findItemIndex(ArrayList<String> items, int item) {
        //折半查找有序元素的索引
        int index = Collections.binarySearch(items, item, new Comparator<Object>() {
            @Override
            public int compare(Object lhs, Object rhs) {
                String lhsStr = lhs.toString();
                String rhsStr = rhs.toString();
                lhsStr = lhsStr.startsWith("0") ? lhsStr.substring(1) : lhsStr;
                rhsStr = rhsStr.startsWith("0") ? rhsStr.substring(1) : rhsStr;
                return Integer.parseInt(lhsStr) - Integer.parseInt(rhsStr);
            }
        });
        if (index < 0) {
            index = 0;
        }
        return index;
    }

    /**
     * Sets selected item.
     *
     * @param year  the year
     */
    public void setSelectedItem(int year) {
        selectedYearIndex = findItemIndex(years, year);

    }

    /**
     * Sets selected item.
     *
     * @param yearOrMonth the year or month
     */
    public void setSelectedItem(int yearOrMonth, int monthOrDay) {

            selectedYearIndex = findItemIndex(years, yearOrMonth);

    }

    /**
     * Sets on date pick listener.
     *
     * @param listener the listener
     */
    public void setOnDatePickListener(OnDatePickListener listener) {
        this.onDatePickListener = listener;
    }

    @Override
    protected View initContentView() {
        int itemWidth = screen.widthPixels;
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        WheelView yearView = new WheelView(activity);
        yearView.setLayoutParams(new LinearLayout.LayoutParams(itemWidth, WRAP_CONTENT));
        yearView.setTextSize(textSize);
        yearView.setTextColor(textColorNormal, textColorFocus);
        yearView.setLineVisible(lineVisible);
        yearView.setLineColor(lineColor);
        yearView.setOffset(offset);
        layout.addView(yearView);
        TextView yearTextView = new TextView(activity);
        yearTextView.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        yearTextView.setTextSize(textSize);
        yearTextView.setTextColor(textColorFocus);
        if (!TextUtils.isEmpty(yearLabel)) {
            yearTextView.setText(yearLabel);
        }
        layout.addView(yearTextView);

            if (!TextUtils.isEmpty(yearLabel)) {
                yearTextView.setText(yearLabel);
            }
            if (selectedYearIndex == 0) {
                yearView.setItems(years);
            } else {
                yearView.setItems(years, selectedYearIndex);
            }
            yearView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
                @Override
                public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                    selectedYearIndex = selectedIndex;
                }
            });


        return layout;
    }

    private int stringToYearMonthDay(String text) {
        if (text.startsWith("0")) {
            //截取掉前缀0以便转换为整数
            text = text.substring(1);
        }
        return Integer.parseInt(text);
    }

    @Override
    protected void setContentViewAfter(View contentView) {
        super.setContentViewAfter(contentView);
        super.setOnConfirmListener(new OnConfirmListener() {
            @Override
            public void onConfirm() {
                if (onDatePickListener != null) {
                    String year = years.get(selectedYearIndex);
                    switch (mode) {
                        case YEAR:
                            ((OnYearPickListener) onDatePickListener).onDatePicked(year);
                            break;
                        case TEXT:
                            ((OnYearPickListener) onDatePickListener).onDatePicked(year);
                            break;

                    }
                }
            }
        });
    }

    /**
     * The interface On date pick listener.
     */
    protected interface OnDatePickListener {

    }



    /**
     * The interface On year month pick listener.
     */
    public interface OnYearPickListener extends OnDatePickListener {

        /**
         * On date picked.
         *
         * @param year  the year
         *
         */
        void onDatePicked(String year);

    }
}
