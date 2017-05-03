package com.example.yuzelli.SmartTour.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;

import butterknife.OnClick;

public class TestActivity extends BaseActivity {
    private static Context context;
    @OnClick(R.id.btn_map)
    public void mapOnclick(){
        MapActivity.actionStart(context);
    }
    @OnClick(R.id.btn_splash)
    public void splashOnclick(){
        Intent intent = new Intent(TestActivity.this, SplashActivity.class);
        startActivity(intent);
    }

    @Override
    protected int layoutInit() {
        return R.layout.activity_test;

    }

    @Override
    protected void binEvent() {
        context =this;
    }
}
