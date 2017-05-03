package com.example.yuzelli.SmartTour.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.esri.android.map.MapView;

import com.esri.android.runtime.ArcGISRuntime;
import com.example.yuzelli.SmartTour.R;
import com.example.yuzelli.SmartTour.base.BaseActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ZoomControls;

import java.util.List;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;

public class MapActivity extends BaseActivity {
    private MapView mMapView = null;
    Button GPS_btn;

    ArcGISTiledMapServiceLayer tileLayer;
    Point point;
    Point wgspoint;
    GraphicsLayer gLayerPos;
    PictureMarkerSymbol locationSymbol;
    LocationManager locMag;
    Location loc;
    Point mapPoint;
    TextView txtview;
    ZoomControls zoomCtrl;
    private Context context;

    @Override
    protected int layoutInit() {
        return R.layout.activity_map;
    }

    @Override
    protected void binEvent() {
        //去除水印
        ArcGISRuntime.setClientId("1eFHW78avlnRUPHm");
        this.mMapView = (MapView) this.findViewById(R.id.map);//设置UI和代码绑定
        tileLayer = new ArcGISTiledMapServiceLayer(
                "http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer");
        mMapView.addLayer(tileLayer);
        context =this;
        zoomCtrl = (ZoomControls) findViewById(R.id.zoomCtrl);
        zoomCtrl.setOnZoomInClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mMapView.zoomin();
            }
        });
        zoomCtrl.setOnZoomOutClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                mMapView.zoomout();
            }
        });
        gLayerPos = new GraphicsLayer();
        mMapView.addLayer(gLayerPos);
        locationSymbol = new PictureMarkerSymbol(this.getResources().getDrawable(R.mipmap.ic_location));
        locMag = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        GPS_btn = (Button) findViewById(R.id.GPS_btn);
        GPS_btn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final List<String> providers = locMag.getProviders(true);
                for (String provider : providers) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    loc = locMag.getLastKnownLocation(provider);
                    LocationListener locationListener = new LocationListener(){
                        public void onLocationChanged(Location location) {
                            markLocation(location);
                        }
                        public void onProviderDisabled(String arg0)
                        {
                        }
                        public void onProviderEnabled(String arg0)
                        {
                        }
                        public void onStatusChanged(String arg0, int arg1, Bundle arg2)
                        {
                        }
                    };
                    locMag.requestLocationUpdates(provider, 2000, 10, locationListener);
                    if(loc!=null)
                    {
                        markLocation(loc);
                    }
                }
            }
        });

    }

    private void markLocation(Location location)
    {
        gLayerPos.removeAll();
        double locx = location.getLongitude();//经度
        double locy = location.getLatitude();//纬度
        wgspoint = new Point(locx, locy);
        //定位到所在位置
        mapPoint = (Point) GeometryEngine.project(wgspoint,SpatialReference.create(4326),mMapView.getSpatialReference());
        Graphic graphic = new Graphic(mapPoint,locationSymbol);
        gLayerPos.addGraphic(graphic);
        mMapView.centerAt(mapPoint, true);
        mMapView.setScale(100);
        mMapView.setMaxResolution(300);
    }
    public static void actionStart(Context context){
        Intent intent = new Intent(context,MapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.pause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.unpause();
    }
}
