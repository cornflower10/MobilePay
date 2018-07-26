package com.qingmang.user;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import com.qingmang.MainActivity;
import com.qingmang.R;
import com.qingmang.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.et_passwd)
    AppCompatEditText etPasswd;
    @BindView(R.id.bt_login)
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        btLogin.setEnabled(false);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(etPhone.getText())
                        &&!TextUtils.isEmpty(etPasswd.getText())){
                    btLogin.setEnabled(true);
                }else {
                    btLogin.setEnabled(false);
                }

            }
        });
        etPasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(etPhone.getText())
                        &&!TextUtils.isEmpty(etPasswd.getText())){
                    btLogin.setEnabled(true);
                }else {
                    btLogin.setEnabled(false);
                }
            }
        });

    }

    @OnClick(R.id.bt_login)
    public void onViewClicked() {
        startActivity(MainActivity.class);
        finish();
    }
}
