package com.example.yuzelli.SmartTour.activity;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.adapter.BannerAdapter;
import com.example.yuzelli.SmartTour.base.BaseActivity;
import com.example.yuzelli.SmartTour.constants.ConstantUtils;
import com.example.yuzelli.SmartTour.entity.UserInfo;
import com.example.yuzelli.SmartTour.utils.ActivityCollectorUtils;
import com.example.yuzelli.SmartTour.utils.MYToast;
import com.example.yuzelli.SmartTour.utils.SharePreferencesUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements  View.OnTouchListener, ViewPager.OnPageChangeListener{
    @Bind(R.id.vp_picture)
     ViewPager vp_picture;   //图片轮播
    @Bind(R.id.tv_vp_title)
     TextView tv_vp_title;   //图片轮播的简介
    @Bind(R.id.ll_Point)
     LinearLayout ll_Point;

    private BannerAdapter adapter;   //图片轮播adapter
    private ArrayList<ImageView> bannerImageDates;   //图片轮播的图片
    private int[] imgs = {R.drawable.scenery1, R.drawable.scenery2, R.drawable.scenery3};
    private View bottomView;
    private int currentIndex = 300;   //图片下标
    private long lastTime;           //上一次图片滚动时间

    private Context context;
    private MainHandler handler;

    @OnClick({R.id.tv_map, R.id.tv_weather, R.id.tv_collection, R.id.tv_user})
    public void onClickViewAction(View v) {
        switch (v.getId()) {
            case R.id.tv_map:
                MapActivity.actionStart(this);
                break;
            case R.id.tv_weather:
                WeatherActivity.actionStart(this);
                break;
            case R.id.tv_collection:
                UserInfo userInfo2 = (UserInfo) SharePreferencesUtil.readObject(context,ConstantUtils.CURRENT_USER);
                if (userInfo2!=null) {
                GatherActivity.actionStart(this);
                }else {
                    LoginActivity.actionStart(this);
                }
                break;
            case R.id.tv_user:
                UserInfo userInfo = (UserInfo) SharePreferencesUtil.readObject(context,ConstantUtils.CURRENT_USER);
                if (userInfo!=null) {
                    PersonActivity.actionStart(this,userInfo);
                }else {
                    LoginActivity.actionStart(this);
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected int layoutInit() {
        return R.layout.activity_main;
    }

    @Override
    protected void binEvent() {
        context = this;
        handler = new MainHandler();
        updataBanner();
    }

    /**
     * 更新图片轮播
     */
    private void updataBanner() {
        bannerImageDates = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView img = new ImageView(context);
            //显示图片的配置
            img.setImageResource(imgs[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            bannerImageDates.add(img);
        }
        adapter = new BannerAdapter(context, bannerImageDates);
        vp_picture.setOnTouchListener(this);
        vp_picture.setAdapter(adapter);
        vp_picture.setCurrentItem(300);
        vp_picture.addOnPageChangeListener(this);
        handler.postDelayed(runnableForBanner, 2000);
        addPoint();
        vp_picture.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                monitorPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    // 设置轮播时间间隔
    private Runnable runnableForBanner = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - lastTime >= 3000) {
                vp_picture.setCurrentItem(currentIndex);
                currentIndex++;
                lastTime = System.currentTimeMillis();
            }
            handler.postDelayed(runnableForBanner, 3000);
        }
    };

    /**
     * 添加小圆点
     */
    private void addPoint() {
        // 1.根据图片多少，添加多少小圆点
        ll_Point.removeAllViews();

        for (int i = 0; i < imgs.length; i++) {
            LinearLayout.LayoutParams pointParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            pointParams.gravity = Gravity.CENTER_VERTICAL;
            if (i < 1) {
                pointParams.setMargins(0, 0, 0, 0);
            } else {
                pointParams.setMargins(10, 0, 0, 0);
            }
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(pointParams);
            iv.setBackgroundResource(R.drawable.point_normal);
            ll_Point.addView(iv);
        }
        ll_Point.getChildAt(0).setBackgroundResource(R.drawable.point_select);
    }

    /**
     * 判断小圆点
     *
     * @param position
     */
    private void monitorPoint(int position) {
        int current = (position - 300) % imgs.length;
        for (int i = 0; i < imgs.length; i++) {
            if (i == current) {
                ll_Point.getChildAt(current).setBackgroundResource(
                        R.drawable.point_select);
            } else {
                ll_Point.getChildAt(i).setBackgroundResource(
                        R.drawable.point_normal);
            }
        }

    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        //连续按2次返回键退出
        if ((System.currentTimeMillis() - exitTime) > 1000) {
            MYToast.show("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {

            ActivityCollectorUtils.getAppManager().AppExit(this);
            System.exit(0);
        }
        super.onBackPressed();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    String [] titles= {"古树青瓦蓝天","孤舟泛湖游景","错落有致风情"};
    @Override
    public void onPageSelected(int position) {
        currentIndex = position;
        lastTime = System.currentTimeMillis();
        //设置轮播文字改变
        final int index = position % bannerImageDates.size();
        tv_vp_title.setText(titles[index]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
