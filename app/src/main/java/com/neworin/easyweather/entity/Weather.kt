package com.neworin.easyweather.entity

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class Weather : BaseEntity() {
    var aqi: Aqi? = null
    var basic: Basic? = null
    var now: Now? = null
    var status: String? = null
    var daily_forecast: List<DailyForecast>? = null
    override fun toString(): String {
        return "Weather(aqi=$aqi, basic=$basic, now=$now, status=$status)"
    }

}
