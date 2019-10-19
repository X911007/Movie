package com.bw.movie.util;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * author: Xuexiandong
 * data: 2019/10/16 11:11:16
 * function：地图（定位）
 */
public class AMapLocationUtil {
    //声明AMapLocationClient类对象
    private static AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    private static AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    private static AMapLocationClientOption mLocationOption = null;

    //定义接口
    public interface AMapInterface {
        void getAMapLocation(AMapLocation aMapLocation);
    }

    //调用定位方法
    public static void getLoaction(final AMapInterface aMapInterface) {
        //初始化定位回调监听器
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                aMapInterface.getAMapLocation(aMapLocation);
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(App.sContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化定位
        mLocationClient = new AMapLocationClient(App.sContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null != mLocationClient){
            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }

        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }
}
