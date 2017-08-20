package com.neworin.easyweather.widget.model

import com.neworin.easyweather.entity.heweather.H5Weather
import com.neworin.easyweather.http.ServiceGenerator
import retrofit2.Callback

/**
 * Copyright (C), 2011-2017 91账单
 * Author: zhangfubin
 * Email: zhangfubin@91zdan.com
 * Description:
 */
class WidgetModelImpl : WidgetModel {

    override fun getDailyWeather(city: String, callback: Callback<H5Weather>) {
        val call = ServiceGenerator.createService().getDailyWeather(city)
        call.enqueue(callback)
    }

    override fun getHourlyWeather(city: String, callback: Callback<H5Weather>) {
        val call = ServiceGenerator.createService().getByCityName(city)
        call.enqueue(callback)
    }

    override fun getWeatherByCityName(city: String, callback: Callback<H5Weather>) {
        val call = ServiceGenerator.createService().getByCityName(city)
        call.enqueue(callback)
    }
}