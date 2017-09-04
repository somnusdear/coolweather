package com.somnusdear.coolweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.somnusdear.coolweather.db.City;
import com.somnusdear.coolweather.db.Country;
import com.somnusdear.coolweather.db.Province;
import com.somnusdear.coolweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 解析json数据
 * 省、市、县
 * Created by Administrator on 2017/9/2.
 */

public class Utility {

    /**
     * 解析、处理服务器返回的省级json数据
     * */
    public static boolean handleProvinceResponse(String response){
        if( !TextUtils.isEmpty(response)){
            try
            {
                JSONArray ProvinceArr = new JSONArray(response);
                for(int i = 0 ;i < ProvinceArr.length() ; i ++){
                    JSONObject provinceObj = ProvinceArr.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObj.getString("name"));
                    province.setProvinceCode(provinceObj.getInt("id"));
                    province.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析、处理服务器返回的市级json数据
     * */
    public static boolean handleCityResponse(String response,int provinceId){
        if(! TextUtils.isEmpty(response)){
            try {
                JSONArray cityArr = new JSONArray(response);
                for(int i = 0;i < cityArr.length();i++){
                    JSONObject cityObj = cityArr.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObj.getString("name"));
                    city.setCityCode(cityObj.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析、处理服务器返回的县级json数据
     * */
    public static boolean handleCountryResponse(String response,int cityId){
        if(! TextUtils.isEmpty(response)){
            try {
                JSONArray countryArr = new JSONArray(response);
                for(int i = 0;i < countryArr.length(); i ++){
                    JSONObject countryObj = countryArr.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countryObj.getString("name"));
                    country.setWeatherId(countryObj.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject weatherObj = new JSONObject(response);
            JSONArray weatherArr = weatherObj.getJSONArray("HeWeather");
            String weatherContent = weatherArr.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
