package com.qingmang;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonPresenter;
import com.qingmang.base.CommonView;
import com.qingmang.view.NumberKeyMainBoard;

import java.text.DecimalFormat;

import butterknife.BindView;
import scan.ScannerActivity;

public class ConsumeActivity extends BaseMvpActivity<CommonPresenter,CommonView> implements CommonView {
    private String payMomey;
    @BindView(R.id.cal_edit_result)
    TextView calResultTextView;
    @BindView(R.id.keyBoard_num)
    com.qingmang.view.NumberKeyMainBoard numKeyBoard;


    private String checkMomey(String value){
        if(TextUtils.isEmpty(value)){
//            showTip("请输入交易金额");
            return "";
        }
        double money=0;
        try {
            money=Double.parseDouble(value);
        } catch (Exception e) {
//            showTip("输入金额格式错误");
            return "";
        }
        if(money<=0){
//            showTip("交易金额要大于0");
            return "";
        }
        DecimalFormat decfmat = new DecimalFormat("#######0.00");
        return decfmat.format(money);
    }

    @Override
    public String setTitleName() {
        return "消费";
    }

    @Override
    public View getRootView() {
        return null;
    }


    @Override
    public int setContentView() {
        return R.layout.activity_consume;
    }

    @Override
    public void initViewAndData() {
        numKeyBoard.setTextView(calResultTextView);
        numKeyBoard.setOnNKBListener(new NumberKeyMainBoard.numberKeyBoardListener() {
            @Override
            public void getResult(String result) {
                if(TextUtils.isEmpty(result)){
                    result = "0.00";
                }
                payMomey = result;
            }

            @Override
            public void getInputNumber(String str) {
                if(TextUtils.isEmpty(str)){
                    calResultTextView.setText("￥ 0.00");
                }else {
                    calResultTextView.setText("￥ "+str);
                }
            }

            @Override
            public void getEqualNumber(String str) {
                if(TextUtils.isEmpty(str)){
                    calResultTextView.setText("￥ 0.00");
                }else {
                    calResultTextView.setText("￥ "+str);
                }
            }

            @Override
            public void OK() {
                startActivity(ScannerActivity.class);
            }
        });
    }

    @Override
    protected CommonPresenter initPresenter() {
        return new CommonPresenter();
    }


    @Override
    public void onDataSuccess(Object o) {

    }

    @Override
    public void onError(String msg) {

    }
}
