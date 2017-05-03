package com.example.yuzelli.SmartTour.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;


import com.example.yuzelli.SmartTour.constants.ConstantUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/14.
 */

public class ImageUtils {
    // 图片转为文件
    public static boolean saveBitmap2file(Bitmap bmp,String fileName) {
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            // 判断SDcard状态
            if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                // 错误提示
                return false;
            }
            // 检查SDcard空间
            File SDCardRoot = Environment.getExternalStorageDirectory();
            if (SDCardRoot.getFreeSpace() < 10000) {
                // 弹出对话框提示用户空间不够
                return false;
            }
            // 在SDcard创建文件夹及文件
            File bitmapFile = new File(SDCardRoot.getPath(),fileName);
            bitmapFile.getParentFile().mkdirs();// 创建文件夹
            stream = new FileOutputStream(SDCardRoot.getPath() + fileName);// "/sdcard/"

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }

    public static Bitmap readBitmap(String fileName){
        Bitmap bitmap = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory().getPath()+fileName);
            if (!f.exists()){
                return null;
            }
            FileInputStream is = new FileInputStream(f);
            byte[] b = new byte[is.available()];
            is.read(b);
             bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

            Log.d("--readBitmap","success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}
