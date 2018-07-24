package com.qingmang.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingmang.R;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2015/10/28.
 * 计算机键盘
 */
public class NumberKeyMainBoard extends LinearLayout {
    private Context mContext;
    //    private EditText mInputView;
    private TextView mTextView;
    private LayoutInflater mInflater;
    private String currentStr = "";
    private double calResult = 0;
    private int calSymbol = 1;
    private boolean canCal = true;
    private numberKeyBoardListener kBL;
    private String inputStr = "";
    private String calStr = "";

    public NumberKeyMainBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public NumberKeyMainBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    protected void initView(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.view_mnumber_keyboard, this);
//        initBtns();
        initBtnst();
    }

    public void setTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    private void initBtnst() {
        findViewById(R.id.keyboard_bt_0).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputStr.length() > 0 && inputStr.length() < 10) {
                    inputStr = inputStr + "0";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }

                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "1";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "2";

                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "3";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_4).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "4";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_5).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "5";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_6).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "6";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_7).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "7";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_8).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "8";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

        findViewById(R.id.keyboard_bt_9).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canCal) {
                    cleanCal();
                }
                if (inputStr.length() < 10) {
                    inputStr = inputStr + "9";
                    if(mTextView!=null){
                        mTextView.setText(calStr + transStr(inputStr));
                    }
                    calculationResult();
                }
            }
        });

//        findViewById(R.id.keyboard_bt_add).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (inputStr.length() > 0 || !canCal) {
//                    canCal = true;
//                    calStr = calStr + transStr(inputStr);
//                    inputStr = "";
//                    calStr = calStr + "+";
//                    if(mTextView!=null){
//                        mTextView.setText(calStr);
//                    }
//                }
//            }
//        });

//        findViewById(R.id.keyboard_bt_minus).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (inputStr.length() > 0 || !canCal) {
//                    canCal = true;
//                    calStr = calStr + transStr(inputStr);
//                    inputStr = "";
//                    calStr = calStr + "-";
//                    if(mTextView!=null){
//                        mTextView.setText(calStr);
//                    }
//                }
//            }
//        });

        findViewById(R.id.keyboard_bt_dot).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        findViewById(R.id.keyboard_bt_clean).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCal();
            }
        });

//        findViewById(R.id.keyboard_bt_equal).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputStr = "";
//                canCal = false;
//                DecimalFormat df;
//                df = new DecimalFormat("######0.00");
//                currentStr = df.format(calResult);
//                mTextView.setText(currentStr);
//                calStr = currentStr.replace("\\.", "");
//                kBL.getEqualNumber(df.format(calResult));
//
//            }
//        });

//        findViewById(R.id.keyboard_bt_backspace).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!canCal) {
//                    cleanCal();
//                    return;
//                }
//                String text = mTextView.getText().toString().trim();
//                if (text.length() <= 0) return;
//                boolean haveCal = false;
//                if (text.substring(text.length() - 1).equals("+") || text.substring(text.length() - 1).equals("-")) {
//                    text = text.substring(0, text.length() - 1);
//                    haveCal = true;
//                }
//                String[] textStr = text.split("[+,-]");
//                String tempStr = textStr[textStr.length - 1];
//                inputStr = removeDot(tempStr);
//                calStr = text.substring(0, text.length() - tempStr.length());
//                if (!haveCal) {
//                    if (inputStr.length() == 1) {
//                        inputStr = "";
//                    } else {
//                        inputStr = inputStr.substring(0, inputStr.length() - 1);
//                    }
//                }
//
//                if(mTextView!=null){
//                    mTextView.setText(calStr + transStr(inputStr));
//                }
//                calculationResult();
//
//            }
//        });

        findViewById(R.id.keyboard_bt_del).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanCal();
            }
        });


    }

    private String transStr(String str) {
        if (str.isEmpty()) return "";
        Double value = Double.parseDouble(str);
        value = value / 100.0;
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(value);
    }

    private String removeDot(String str) {
        String result = str.replace(".", "");

        for (int i = 0; i < result.length(); i++) {
            if (result.substring(0, 1).equals("0")) {
                result = result.substring(1, result.length());
                i--;
            }
        }
        return result;
    }

    private String strTrans(String str) {
        if (str.isEmpty()) return "";
        Double dv = Double.parseDouble(str);
        Long lv = Math.round(dv * 100);
        return lv + "";
    }

    private void calculationResult() {
        if(mTextView==null) return;
        String text = mTextView.getText().toString().trim();
        if (text.length() > 0) {
            currentStr = "";
            calResult = 0;
            calSymbol = 1;

            for (int i = 0; i < text.length(); i++) {
                    if (!currentStr.equals("")) {
                        calResult = calResult - Double.parseDouble(currentStr) * calSymbol;
                    }
                    currentStr = currentStr + text.substring(i, i + 1);
                    if (!currentStr.isEmpty()) {
                        calResult = calResult + Double.parseDouble(currentStr) * calSymbol;
                    }
            }
        } else {
            calResult = 0;
        }


        DecimalFormat df = new DecimalFormat("######0.00");
        kBL.getResult(df.format(calResult));
        kBL.getInputNumber(transStr(inputStr));
    }

    public void cleanCal() {
        canCal = true;
        mTextView.setText("");
        currentStr = "";
        calResult = 0;
        calSymbol = 1;
        inputStr = "";
        calStr = "";
        calculationResult();
    }

    private boolean canInputNumber() {
        if (currentStr.length() > 0) {
            if (currentStr.contains(".")) {
                String[] strArray = currentStr.split("\\.");
                if (strArray.length > 1 && strArray[1].toString().length() >= 2) {
                    return false;
                }
            } else if (currentStr.length() > 7) {
                return false;
            }
        }
        return true;
    }

    private void btnOnClickListener(int keyboardId, final int keyboardKey) {
        findViewById(keyboardId).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                performInput(keyboardKey);
            }
        });
    }

    private void performInput(int code) {
//        if(null==mInputView){
//            return;
//        }
//        mInputView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,code));
//        mInputView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP,code));
//        mInputView.requestFocus();
    }

    public void setOnNKBListener(numberKeyBoardListener kBL) {
        this.kBL = kBL;
    }

    public interface numberKeyBoardListener {
        void getResult(String result);

        void getInputNumber(String str);
        void OK();
        void getEqualNumber(String str);
    }
}
