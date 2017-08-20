package com.neworin.easyweather.entity.heweather;

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
public class HourlyForecast extends BaseEntity {

    public String date;
    public String tmp;
    public Cond cond;
    public Wind wind;

    class Cond extends BaseEntity {
        public String code;
        public String txt;
    }

    class Wind extends BaseEntity {
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}