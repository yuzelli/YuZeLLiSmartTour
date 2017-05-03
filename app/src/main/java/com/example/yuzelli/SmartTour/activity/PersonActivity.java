package com.example.yuzelli.SmartTour.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;
import com.example.yuzelli.SmartTour.constants.ConstantUtils;
import com.example.yuzelli.SmartTour.entity.UserInfo;
import com.example.yuzelli.SmartTour.utils.ImageUtils;
import com.example.yuzelli.SmartTour.utils.SharePreferencesUtil;
import com.example.yuzelli.SmartTour.widgets.RoundImageView;

import java.io.File;
import java.io.StringReader;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonActivity extends BaseActivity {
    @Bind(R.id.img_userHeadMax)
    RoundImageView img_userHeadMax;
    @Bind(R.id.tv_userName)
    TextView tv_userName;

    @Bind(R.id.rl_collection)
    RelativeLayout rl_collection;
    @Bind(R.id.rl_foot)
    RelativeLayout rl_foot;
    @Bind(R.id.rl_quesion)
    RelativeLayout rl_quesion;
    @OnClick(R.id.rl_exit)
    public void exitOnclick(View v){
        SharePreferencesUtil.saveObject(context,ConstantUtils.CURRENT_USER,null);
        finish();
    }

    /**
     * 定义三种状态
     */
    private static final int HEAD_PORTRAIT_PIC = 1;//相册
    private static final int HEAD_PORTRAIT_CAM = 2;//相机
    private static final int HEAD_PORTRAIT_CUT = 3;//图片裁剪
    private File photoFile;
    private Bitmap photoBitmap;
    private Context context;
    private UserInfo userInfo;

    @Override
    protected int layoutInit() {
        return R.layout.activity_person;
    }

    @Override
    protected void binEvent() {
            context = this;
        userInfo = (UserInfo) SharePreferencesUtil.readObject(context,ConstantUtils.CURRENT_USER);

        Bitmap bitmap = ImageUtils.readBitmap("/"+userInfo.getPhone() + ConstantUtils.AVATAR_FILE_PATH);
        if (bitmap != null) {
            img_userHeadMax.setImageBitmap(bitmap);
        } else {
            img_userHeadMax.setImageResource(R.drawable.ic_default_head);
        }
        img_userHeadMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPhotoDialog();
            }
        });
        if (userInfo.getUserName()==null||userInfo.getUserName().equals("")) {
            tv_userName.setText("用户名未设置，点我设置！");
        } else {
            tv_userName.setText(userInfo.getUserName());
        }
        tv_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AlertDialog.Builder builder = new AlertDialog.Builder(PersonActivity.this);
                //使用xml文件定义视图
                View view = LayoutInflater.from(PersonActivity.this).inflate(R.layout.dialog, null);
                builder.setTitle("请输入用户名");
                EditText et_userName = (EditText) view.findViewById(R.id.et_userName);
                final String name = et_userName.getText().toString().trim();
                builder.setView(view);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (name != null && !name.equals("")) {
                            userInfo.setUserName(name);
                            SharePreferencesUtil.saveObject(context, userInfo.getPhone(), userInfo);
                            tv_userName.setText(name);
                            showToast("修改用户信息成功！");
                        }
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();

            }
        });
        rl_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.actionStart(context);
            }
        });
    }

    //显示Dialog选择拍照还是从相册选择
    private void showPhotoDialog() {
        final Dialog dialog = new Dialog(this, R.style.PhotoDialog);
        final View view = LayoutInflater.from(PersonActivity.this).inflate(R.layout.diallog_personal_head_select, null);
        dialog.setContentView(view);
        TextView tv_PhotoGraph = (TextView) view.findViewById(R.id.tv_personal_photo_graph);
        TextView tv_PhotoAlbum = (TextView) view.findViewById(R.id.tv_personal_photo_album);
        TextView tv_Cancel = (TextView) view.findViewById(R.id.tv_cancel);

        tv_PhotoGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoGraph();
            }
        });

        tv_PhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhotoAlbum();
            }
        });

        tv_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //设置出现Dialog位置
        Window window = dialog.getWindow();
        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        dialog.show();
    }

    //打开相册方法
    private void openPhotoAlbum() {
        Intent picIntent = new Intent(Intent.ACTION_PICK, null);
        picIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(picIntent, HEAD_PORTRAIT_PIC);
    }

    //打开相机方法
    private void openPhotoGraph() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!file.exists()) {
                file.mkdirs();
            }
            photoFile = new File(file, System.currentTimeMillis() + "");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, HEAD_PORTRAIT_CAM);
        } else {

            Toast.makeText(this, "请确认已经插入SD卡", Toast.LENGTH_SHORT).show();
        }
    }

    //回调函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case HEAD_PORTRAIT_CAM:
                    startPhotoZoom(Uri.fromFile(photoFile));
                    break;
                case HEAD_PORTRAIT_PIC:
                    if (data == null || data.getData() == null) {
                        return;
                    }
                    startPhotoZoom(data.getData());
                    break;
                case HEAD_PORTRAIT_CUT:
                    if (data != null) {
                        photoBitmap = data.getParcelableExtra("data");

                        try {
                            if (ImageUtils.saveBitmap2file(photoBitmap, "/"+userInfo.getPhone() + ConstantUtils.AVATAR_FILE_PATH)) {
                                Bitmap bitmap = ImageUtils.readBitmap("/"+userInfo.getPhone() + ConstantUtils.AVATAR_FILE_PATH);
                                img_userHeadMax.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                img_userHeadMax.setImageBitmap(bitmap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    break;
            }
        }

    }

    /**
     * 打开系统图片裁剪功能
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true); //黑边
        intent.putExtra("scaleUpIfNeeded", true); //黑边
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, HEAD_PORTRAIT_CUT);
    }

    public static void actionStart(Context context, UserInfo info) {
        Intent intent = new Intent(context, PersonActivity.class);
        intent.putExtra("userInfo", info);
        context.startActivity(intent);
    }
}
