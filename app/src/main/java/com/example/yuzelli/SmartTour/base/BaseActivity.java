package com.example.yuzelli.SmartTour.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.yuzelli.SmartTour.utils.ActivityCollectorUtils;
import com.example.yuzelli.SmartTour.utils.MYToast;

import butterknife.ButterKnife;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // 隐藏actionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutInit());

        ButterKnife.bind(this);

        ActivityCollectorUtils.getAppManager().addActivity(this);
        binEvent();
    }
    protected abstract int layoutInit();
    protected abstract void binEvent();
    public void showToast(String msg) {
        MYToast.show(msg);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtils.getAppManager().finishActivity();
        //openedActivitys.remove(getClass().getSimpleName());
       ButterKnife.unbind(this);
    }
}
