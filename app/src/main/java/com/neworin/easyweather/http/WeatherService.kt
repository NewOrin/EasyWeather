package com.neworin.easyweather.http

import com.neworin.easyweather.entity.heweather.H5Weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface WeatherService {

    @GET("weather")
    fun getByCityName(@Query("city") city: String): Call<H5Weather>

    @GET("search")
    fun searchCity(@Query("city") city: String): Call<H5Weather>

    @GET("hourly")
    fun getHourlyWeather(@Query("city") city: String): Call<H5Weather>

    @GET("forecast")
    fun getDailyWeather(@Query("city") city: String): Call<H5Weather>
}