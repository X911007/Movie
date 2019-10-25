package com.bw.movie.bean;

/**
 * author: Xuexiandong
 * data: 2019/10/21 15:15:28
 * function：地理位置
 */
public class BeanGeographicLocation {
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public BeanGeographicLocation(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
