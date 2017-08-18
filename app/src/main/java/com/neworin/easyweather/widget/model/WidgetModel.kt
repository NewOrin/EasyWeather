package com.neworin.easyweather.widget.model

import com.neworin.easyweather.entity.H5Weather
import retrofit2.Callback

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
interface WidgetModel {
    fun getWeatherByCityName(city: String, callback: Callback<H5Weather>)

    fun getHourlyWeather(city: String, callback: Callback<H5Weather>)

    fun getDailyWeather(city: String, callback: Callback<H5Weather>)

}