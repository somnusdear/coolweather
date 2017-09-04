package com.somnusdear.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/9/4.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfor comfor;
    @SerializedName("cw")
    public CarWash carWash;
    public Sport sport;
    public class Comfor{
        @SerializedName("txt")
        public String info;
    }
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }
    public class Sport {
        @SerializedName("txt")
        public String info;
    }
}
