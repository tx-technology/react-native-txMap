package com.example.react_native_txMap;

import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/**
 * Created by Admin on 2017/11/23.
 */

public class MapViewManager extends ViewGroupManager<MapView> {
    public static final String REACT_CLASS = "RCTMapView";
    private AMap aMap ;
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MapView createViewInstance(ThemedReactContext reactContext) {
        MapView map = new MapView(reactContext);
        map.onCreate(reactContext.getCurrentActivity().getIntent().getExtras());
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = map.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(300)); // 初始化是视角位置
        return map;
    }
    @Override
    public void onDropViewInstance(MapView view) {
        super.onDropViewInstance(view);
        view.onDestroy();
    }

    @Override
    public void addView(MapView parent, View child, int index) {
        parent.addView(child);
        super.addView(parent, child, index);
    }

    @Override
    public void removeViewAt(MapView parent, int index) {
        parent.removeView(parent.getChildAt(index));
        super.removeViewAt(parent, index);
    }
}
