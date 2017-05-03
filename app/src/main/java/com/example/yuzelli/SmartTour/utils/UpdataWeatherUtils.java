package com.example.yuzelli.SmartTour.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.example.yuzelli.SmartTour.R;


public class UpdataWeatherUtils {



    /**
     * 设置天气图片
     *
     * @param id
     * @return
     */
    public static int setWeatherImg(String id) {
        int src = 0;
        int windid = Integer.valueOf(id);
        switch (windid) {
            case 100:
                src = R.drawable.w100;
                break;
            case 101:
                src = R.drawable.w101;
                break;
            case 103:
                src = R.drawable.w103;
                break;
            case 104:
                src = R.drawable.w104;
                break;
            case 200:
                src = R.drawable.w200;
                break;
            case 201:
                src = R.drawable.w201;
                break;
            case 202:
                src = R.drawable.w202;
                break;
            case 203:
                src = R.drawable.w203;
                break;
            case 204:
                src = R.drawable.w204;
                break;
            case 205:
                src = R.drawable.w205;
                break;
            case 206:
                src = R.drawable.w206;
                break;
            case 207:
                src = R.drawable.w207;
                break;
            case 208:
                src = R.drawable.w208;
                break;
            case 209:
                src = R.drawable.w209;
                break;
            case 210:
                src = R.drawable.w210;
                break;
            case 211:
                src = R.drawable.w211;
                break;
            case 212:
                src = R.drawable.w212;
                break;
            case 213:
                src = R.drawable.w213;
                break;
            case 300:
                src = R.drawable.w300;
                break;
            case 301:
                src = R.drawable.w301;
                break;
            case 302:
                src = R.drawable.w302;
                break;
            case 303:
                src = R.drawable.w303;
                break;
            case 304:
                src = R.drawable.w304;
                break;
            case 305:
                src = R.drawable.w305;
                break;
            case 306:
                src = R.drawable.w306;
                break;
            case 307:
                src = R.drawable.w307;
                break;
            case 308:
                src = R.drawable.w308;
                break;

            case 309:
                src = R.drawable.w309;
                break;
            case 310:
                src = R.drawable.w310;
                break;
            case 311:
                src = R.drawable.w311;
                break;
            case 312:
                src = R.drawable.w312;
                break;
            case 313:
                src = R.drawable.w313;
                break;
            case 400:
                src = R.drawable.w400;
                break;
            case 401:
                src = R.drawable.w401;
                break;
            case 402:
                src = R.drawable.w402;
                break;
            case 403:
                src = R.drawable.w403;
                break;
            case 404:
                src = R.drawable.w404;
                break;
            case 405:
                src = R.drawable.w405;
                break;
            case 406:
                src = R.drawable.w406;
                break;
            case 407:
                src = R.drawable.w407;
                break;
            case 500:
                src = R.drawable.w500;
                break;
            case 501:
                src = R.drawable.w501;
                break;
            case 502:
                src = R.drawable.w502;
                break;
            case 503:
                src = R.drawable.w503;
                break;
            case 504:
                src = R.drawable.w504;
                break;
            case 507:
                src = R.drawable.w507;
                break;
            case 508:
                src = R.drawable.w508;
                break;
            case 900:
                src = R.drawable.w900;
                break;
            case 901:
                src = R.drawable.w901;
                break;
            case 999:
                src = R.drawable.w999;
                break;
            default:
                src = R.drawable.ww;
                break;
        }
        return src;
    }
    /**
     * 设置空气质量的指示图片
     * @param brf
     * @return
     */
    public static Drawable getAirHintImg(Context context,String brf) {
        int index = Integer.valueOf(brf);
        Drawable airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_0_50);
        if (index <= 50) {
            airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_0_50);
        } else if (50 < index && index <= 100) {
            airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_51_100);
        } else if (100 < index && index <= 150) {
            airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_101_150);
        } else if (150 < index && index <= 200) {
            airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_151_200);
        } else if (200 < index && index <= 300) {
            airHintImg = ContextCompat.getDrawable(context, R.mipmap.biz_plugin_weather_201_300);
        }
        return airHintImg;
    }
}
