package com.neworin.easyweather.entity;

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
public class DailyForecast extends BaseEntity {

    public String date;
    public Tmp tmp;

    public class Tmp extends BaseEntity{
        public String max;
        public String min;
    }
}
