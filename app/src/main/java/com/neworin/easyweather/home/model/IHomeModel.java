package com.neworin.easyweather.home.model;

import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.entity.Weather;

import java.util.List;

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