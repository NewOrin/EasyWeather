package com.neworin.easyweather.module.home.model;

import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.http.ServiceGenerator;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public class HomeModelImpl implements IHomeModel {

    @Override
    public void getWeatherByCityName(@NotNull String city, Callback<H5Weather> callback) {
        Call<H5Weather> call = ServiceGenerator.Companion.createService().getByCityName(city);
        call.enqueue(callback);
    }

    @Override
    public void searchCity(String city, Callback<H5Weather> callback) {
        Call<H5Weather> call = ServiceGenerator.Companion.createService().searchCity(city);
        call.enqueue(callback);
    }
}