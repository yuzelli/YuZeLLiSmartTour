package com.example.yuzelli.SmartTour.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.esri.core.internal.catalog.User;
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

public class GatherActivity extends BaseActivity {

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
        return R.layout.activity_gather;
    }

    @Override
    protected void binEvent() {
        context = this;
        userInfo = (UserInfo) SharePreferencesUtil.readObject(this, ConstantUtils.CURRENT_USER);
        gatherList = new ArrayList<>();
        gatherList.add(new Gather(1, "[首都机场] 首都机场实利樱桃采摘园", "2017/5/15", "2017/6/10", "40.064335", "116.58568", "39元/斤", "13811963236", "	天竺地区二十里堡村"));
        gatherList.add(new Gather(2, "	顺利樱桃采摘园", "2017/5/9", "2017/6/10	", "40.061659", "116.14310", "60元/斤", "13911188763", "苏家坨镇周家巷村"));
        gatherList.add(new Gather(3, "凤清樱桃采摘园", "2017/5/25", "2017/6/25", "40.069393", "116.13806", "50元/斤", "13717742009", "苏家坨镇北安河村东"));
        gatherList.add(new Gather(4, "振海樱桃采摘园", "2017/5/21", "2017/6/20", "40.063331", "116.14244", "40元/斤", "15510559510", "周家巷村西雅图强商店向北300米丁字路口右转100米"));
        gatherList.add(new Gather(5, "德民农场采摘园", "2017/5/18", "2017/6/15", "40.057969", "116.13137", "100元/斤", "15911127760", "苏家坨镇周家巷村大觉寺东站(绿色樱桃园对面)"));
        gatherList.add(new Gather(6, "老张樱桃采摘园", "2017/5/10", "2017/6/15", "40.091958", "116.10115", "39元/斤", "13521437648", "苏家坨镇七王坟村(七王坟社区服务中心)"));
        gatherList.add(new Gather(7, "老马樱桃采摘园", "2017/5/15", "2017/6/15", "40.032213", "116.18242", "60元/斤", "62487688", "温泉镇白家疃簸箕水27号"));
        gatherList.add(new Gather(8, "福森生态园", "2017/5/15", "2017/6/10", "39.805238", "116.91392", "50元/斤", "13051785621", "	西集镇小灰店村63号"));
        gatherList.add(new Gather(9, "芗菋原生态樱桃采摘园", "2017/5/7", "2017/6/15", "39.808658", "116.86622", "60元/斤", "13581542803", "政府路西集镇张各庄村"));
        gatherList.add(new Gather(10, "田树凤樱桃采摘园", "2017/5/15", "2017/6/25", "39.815460", "116.79881", "50元/斤", "15611717119", "西集镇儒林村24号(北京阳光国际会议中心)"));
        gatherList.add(new Gather(11, "立国樱桃采摘园", "2017/5/7", "2017/6/20", "39.824003", "116.79797", "40元/斤", "13164262650", "	西集镇沙古堆村运河大堤路(大运河左堤路)"));
        gatherList.add(new Gather(12, "上林苑樱桃采摘园", "2017/5/10", "2017/6/14", "39.801438", "116.81215", "60元/斤", "18701209495", "京哈高速阳光国际会议中心南400米(西集镇儒林村南侧)"));
        gatherList.add(new Gather(13, "天润通樱桃园", "2017/5/22", "2017/6/30", "40.260013 ", "116.23170", "40元/斤", "13716327101", "十三陵神南路1号天润通采摘园(神路入口右侧南新村入口处)"));
        gatherList.add(new Gather(14, "开心采摘园", "2017/5/15", "2017/6/24", "40.266502", "116.22455", "40元/斤", "15001283681", "十三陵镇康陵园村(近菜园子主题餐厅)"));
        gatherList.add(new Gather(15, "朴悦农场", "2017/5/10", "2017/6/20", "40.116508", "116.36555", "58元/斤", "60777780", "	白各庄新村上水庄园草莓采摘园东南300米"));
        gatherList.add(new Gather(16, "北京绿然康采摘园", "2017/5/15", "2017/6/20", "40.168334", "116.49265", "50元/斤", "15210070924", "高丽营镇京城高速高丽营南桥"));
        gatherList.add(new Gather(17, "大芦荡樱桃采摘园", "2017/5/10", "2017/6/15", "40.142036", "116.77791", "40元/斤", "18901132966", "于辛庄村南(李木路，近汉石桥湿地)"));
        gatherList.add(new Gather(18, "樱桃幽谷王进有采摘园", "2017/5/22", "2017/6/20", "40.208577", "116.92169", "40元/斤", "51654966", "龙湾屯镇山里辛庄村(近樱桃幽谷采摘园)"));
        gatherList.add(new Gather(19, "大地富邦樱桃采摘园", "2017/5/20", "2017/6/20", "40.244226", "116.53977", "60元/斤", "13810970994", "京承高速12出口,(天北路出口)往南100米(安妮农庄旁)"));
        gatherList.add(new Gather(20, "幽谷人家樱桃采摘园", "2017/5/22", "2017/6/20", "40.208577", "116.92169", "40元/斤", "13811218722", "龙湾屯镇山里辛庄村，杜数义樱桃采摘园(近焦庄户地道战遗址纪念馆 安利隆生态山庄)"));
        gatherList.add(new Gather(21, "华田采摘园", "2017/5/7", "2017/6/15", "39.688378", "116.02856", "25元/斤", "13801016070", "城关田各庄村东"));
        gatherList.add(new Gather(22, "四季青果林所", "2017/5/7", "2017/6/30", "39.978491", "116.23655", "50元/斤", "62858875", "闵庄路68号"));
        gatherList.add(new Gather(23, "大周末采摘园", "2017/5/20", "2017/6/25", "40.100095", "116.12668", "90元/斤", "", "北安河路69号附近"));
        gatherList.add(new Gather(24, "白家疃观光采摘园", "2017/5/20", "2017/6/25", "40.048775", "116.17026", "100元/斤", "62454358", "海淀区温泉镇白家疃国家档案馆西侧"));
        gatherList.add(new Gather(25, "顺彩新特果林种植中心", "2017/5/22", "2017/6/20", "40.142013 ", "116.77788", "90元/斤", "89466006", "顺义区南法信地区西杜兰村"));
        gatherList.add(new Gather(26, "台湖第五生产队", "2017/5/25", "2017/6/5", "39.832070", "116.64361", "40元/斤", "61536137", "通州台湖镇台湖村"));
        gatherList.add(new Gather(27, "周家巷卫兵樱桃采摘园", "2017/5/22", "2017/6/20", "40.070575", "116.14597", "50元/斤	", "13439068001", "	北京市海淀区苏家坨镇周家巷村平房158号"));
        gatherList.add(new Gather(28, "微庄园火龙庄", "2017/5/20", "2017/6/30", "40.199450", "116.17097", "50元/斤", "4006668919", "北京市昌平区马池口镇政府北800米华彬庄园对面"));
        gatherList.add(new Gather(29, "洼里乡居楼", "2017/5/28", "2017/6/30", "40.188173", "116.46505", "50元/斤", "61714090", "北京市昌平区小汤山镇南官庄村西北侧400米"));
        gatherList.add(new Gather(30, "印象山庄", "2017/5/20", "2017/6/14", "40.312648", "116.20249", "40元/斤", "60764560", "昌平区十三陵镇燕子口村26号"));
        gatherList.add(new Gather(31, "御林汤泉度假村", "2017/5/22", "2017/6/25", "40.181386", "116.36400", "60元/斤", "57100036	", "北京昌平小汤山国家农业科技园西区(中国航空博物馆路西)"));
        gatherList.add(new Gather(32, "大河套农庄", "2017/5/28", "2017/6/30", "40.290987", "116.22740", "60元/斤", "60762551", "北京昌平区八达岭高速西关出口十三陵方向昭陵村村南"));
        gatherList.add(new Gather(33, "神路葡萄庄园", "	2017/5/28", "2017/6/30", "40.265832", "116.23316", "50元/斤", "13051497133", "昌平区十三陵神路西侧"));
        gatherList.add(new Gather(34, "北京十三陵盛龙农业合作社", "2017/5/22", "2017/6/30", "40.304899", "116.26171", "40元/斤", "13716766312", "十三陵镇大宫门村路西"));
        gatherList.add(new Gather(35, "华严小镇生态观光采摘园", "2017/5/21", "2017/6/3", "39.902332", "116.03323", "50元/斤", "", "北京市门头沟区潭柘寺镇平原村。"));
        gatherList.add(new Gather(36, "北京恒蚨桂有机农业采摘园", "2017/5/21", "2017/6/30", "39.899762", "116.04121", "50元/斤	", "", "北京市门头沟区潭柘寺镇桑峪村"));
        gatherList.add(new Gather(37, "明太阳樱桃采摘", "2017/5/21", "2017/6/25", "39.818937", "116.89676", "50元/斤", "", "西集镇通香路沙古堆村"));
        gatherList.add(new Gather(38, "北京树梅溪采摘园", "2017/5/21", "2017/6/25", "40.047472", "116.05617", "50元/斤", "", "门头沟区妙峰山镇樱桃沟村"));
        ArrayList<Integer> paixu = new ArrayList<>();
        for (Gather gather : gatherList){
            String beiginTime = gather.getBeginTime();
            beiginTime = beiginTime.replaceAll("/","");
            paixu.add(Integer.valueOf(beiginTime));


        }


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
        lv_gather.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showDetailDialog(gatherList.get(position));
                showToast("经度：" + gatherList.get(position).getLoctionX() + "\n纬度：" + gatherList.get(position).getLoctionY());
            }
        });
    }

    private void showDetailDialog(final Gather gather) {
        final Dialog dialog = new Dialog(this, R.style.PhotoDialog);
        final View view = LayoutInflater.from(GatherActivity.this).inflate(R.layout.dialog_content, null);
        dialog.setContentView(view);
        TextView tv_Cancel = (TextView) view.findViewById(R.id.tv_cancel);
        final TextView tv_collection = (TextView) view.findViewById(R.id.tv_collection);
        TextView tv_locationx = (TextView) view.findViewById(R.id.tv_locationx);
        TextView tv_locationy = (TextView) view.findViewById(R.id.tv_locationy);
        tv_locationx.setText("经度：" + gather.getLoctionX());
        tv_locationy.setText("纬度：" + gather.getLoctionY());
        tv_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Gather> lists = (ArrayList<Gather>) SharePreferencesUtil.readObject(context, userInfo.getPhone() + ConstantUtils.COLLECTION_GATHER);
                if (lists == null) {
                    lists = new ArrayList<Gather>();
                }
                lists.add(gather);
                SharePreferencesUtil.saveObject(context, userInfo.getPhone() + ConstantUtils.COLLECTION_GATHER, lists);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GatherActivity.class);
        context.startActivity(intent);
    }
}
