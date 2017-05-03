package com.example.yuzelli.SmartTour.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;
import com.example.yuzelli.SmartTour.constants.ConstantUtils;
import com.example.yuzelli.SmartTour.entity.Gather;
import com.example.yuzelli.SmartTour.entity.UserInfo;
import com.example.yuzelli.SmartTour.utils.CommonAdapter;
import com.example.yuzelli.SmartTour.utils.SharePreferencesUtil;
import com.example.yuzelli.SmartTour.utils.ViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {
    private ArrayList<Gather> gatherList;
    private UserInfo userInfo;
    private Context context;
    @Bind(R.id.lv_gather)
    ListView lv_gather;

    @OnClick(R.id.img_back)
    public void backOnClick(View view) {
        finish();
    }

    @Override
    protected int layoutInit() {
        return R.layout.activity_collection;
    }

    @Override
    protected void binEvent() {
        context = this;
        userInfo = (UserInfo) SharePreferencesUtil.readObject(this, ConstantUtils.CURRENT_USER);
        gatherList = (ArrayList<Gather>) SharePreferencesUtil.readObject(context, userInfo.getPhone() + ConstantUtils.COLLECTION_GATHER);
        lv_gather.setAdapter(new CommonAdapter<Gather>(this, gatherList, R.layout.cell_gather_item) {
            @Override
            public void convert(ViewHolder helper, Gather item) {
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_phone, "电话：" + item.getPhone());
                helper.setText(R.id.tv_price, "价格：" + item.getPrice());
                helper.setText(R.id.tv_beginTime, "开始时间：" + item.getBeginTime());
                helper.setText(R.id.tv_endTime, "结束时间：" + item.getEndTime());
                helper.setText(R.id.tv_address, "地址：" + item.getAddress());
            }
        });
        lv_gather.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                deleteDialog(position);
                return false;
            }
        });
    }

    private void deleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示框");
        builder.setMessage("你确定要删除么");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gatherList.remove(position);
                SharePreferencesUtil.saveObject(context,userInfo.getPhone()+ConstantUtils.COLLECTION_GATHER,gatherList);
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }
}
