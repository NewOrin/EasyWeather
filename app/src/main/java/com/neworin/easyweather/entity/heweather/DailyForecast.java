package com.neworin.easyweather.entity.heweather;

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
public class DailyForecast extends BaseEntity {

    public String date;
    public Tmp tmp;
    public Cond cond;
    public Wind wind;


    public class Tmp extends BaseEntity{
        public String max;
        public String min;
    }

    public class Cond extends BaseEntity {
        public String code_d;
        public String code_n;
        public String txt_d;
        public String txt_n;
    }

    public class Wind extends BaseEntity {
        public String deg;
        public String dir;
        public String sc;
        public String spd;
    }
}
