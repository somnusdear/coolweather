package com.somnusdear.coolweather.gson;

/**
 * Created by Administrator on 2017/9/4.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
