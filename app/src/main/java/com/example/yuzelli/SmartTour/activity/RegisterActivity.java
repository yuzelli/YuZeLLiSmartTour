package com.example.yuzelli.SmartTour.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.core.internal.catalog.User;
import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;
import com.example.yuzelli.SmartTour.entity.UserInfo;
import com.example.yuzelli.SmartTour.utils.LoginUtils;
import com.example.yuzelli.SmartTour.utils.SharePreferencesUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_userPhone;   //用户注册手机号
    private EditText et_passWord;   //密码
    private EditText et_certainPassWord;  //确认密码
    private Button btn_register;   //注册按钮

    private Context context;
    private UserInfo userInfo;
    private boolean agreedProtocol;


    @Override
    protected int layoutInit() {
        return R.layout.activity_register;
    }

    @Override
    protected void binEvent() {
        context = this;
        initView();
    }

    private void initView() {
        et_userPhone = (EditText) this.findViewById(R.id.et_userPhone);
        et_passWord = (EditText) this.findViewById(R.id.et_passWord);
        et_certainPassWord = (EditText) this.findViewById(R.id.et_certainPassWord);
        btn_register = (Button) this.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    /**
     * 跳转
     *
     * @param context
     */
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                doRegisterUser();
                break;
            default:
                break;
        }
    }

    private void doRegisterUser() {
        String userPhone = et_userPhone.getText().toString();
        String passWord = et_passWord.getText().toString();
        String certainPassWord = et_certainPassWord.getText().toString();
        if(!verificationUserInfo(userPhone, passWord, certainPassWord)){
            return;
        }
        UserInfo userInfo = new UserInfo(userPhone,passWord);
        SharePreferencesUtil.saveObject(context,userPhone,userInfo);
        if (SharePreferencesUtil.readObject(context,userPhone)!=null){
            Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(context, "注册失败！", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 验证用户输入
     *
     * @param userPhone
     * @param passWord
     * @param certainPassWord
     */
    private boolean verificationUserInfo(String userPhone, String passWord, String certainPassWord) {
        boolean flag = true;
        if (!LoginUtils.isPhoneEnable(userPhone)) {
            showToast("用户手机号输入有误");
            et_userPhone.setText("");
            flag = false;
        }
        if (SharePreferencesUtil.readObject(context,userPhone)!=null){
            showToast("用户已注册");

        }
        if (!passWord.equals(certainPassWord)) {
            showToast("两次密码不一致");
            et_certainPassWord.setText("");
            flag = false;
        }

        return flag;
    }

}
