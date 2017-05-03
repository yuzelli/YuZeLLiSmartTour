package com.example.yuzelli.SmartTour.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;
import com.example.yuzelli.SmartTour.constants.ConstantUtils;
import com.example.yuzelli.SmartTour.entity.UserInfo;
import com.example.yuzelli.SmartTour.utils.LoginUtils;
import com.example.yuzelli.SmartTour.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class    LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_userPhone;   //用户账号
    private EditText et_passWord;   //用户密码
    private Button btn_login;      //登录按钮
    private TextView tv_register;   //注册按钮
    private RelativeLayout activity_login;  //根布局
    private Context context;
    private UserInfo userInfo;



    @Override
    protected int layoutInit() {
        return R.layout.activity_login;
    }

    @Override
    protected void binEvent() {
        context = this;
        initView();
    }

    private void initView() {
        et_userPhone = (EditText) this.findViewById(R.id.et_userPhone);
        et_passWord = (EditText) this.findViewById(R.id.et_passWord);
        btn_login = (Button) this.findViewById(R.id.btn_login);
        tv_register = (TextView) this.findViewById(R.id.tv_register);
        activity_login = (RelativeLayout) this.findViewById(R.id.activity_login);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        activity_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                return imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                doLogin();
                break;
            case R.id.tv_register:
                RegisterActivity.actionStart(context);
            default:
                break;
        }

    }

    private void doLogin() {
        String userPhone = et_userPhone.getText().toString();
        String passWord = et_passWord.getText().toString();
        if (!LoginUtils.isPhoneEnable(userPhone)) {
            et_userPhone.setText("");
            return;
        }

       UserInfo userInfo = (UserInfo) SharePreferencesUtil.readObject(context,userPhone);
        if (userInfo==null){
            showToast("当前用户未注册！");
        }
       else {
            if (userInfo.getPassword().equals(passWord)){
                showToast("用户登陆");
                SharePreferencesUtil.saveObject(context, ConstantUtils.CURRENT_USER,userInfo);
                finish();
            }else {
                showToast("密码错误！");
                et_passWord.setText("");
            }
        }

    }

    /**
     * 跳转
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


}
