package com.neworin.easyweather.module.home.model;

import com.neworin.easyweather.entity.heweather.H5Weather;

import retrofit2.Callback;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public interface IHomeModel {

    void getWeatherByCityName(String city, Callback<H5Weather> callback);

    void searchCity(String city, Callback<H5Weather> callback);
}