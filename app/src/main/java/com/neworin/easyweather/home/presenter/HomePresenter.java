package com.neworin.easyweather.home.presenter;

import android.content.Context;
import android.util.Log;

import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.entity.Weather;
import com.neworin.easyweather.home.model.HomeModelImpl;
import com.neworin.easyweather.home.model.IHomeModel;
import com.neworin.easyweather.home.view.IHomeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public class HomePresenter {

    private IHomeView mHomeView;
    private IHomeModel mHomeModel;
    private Context mContext;

    public HomePresenter(IHomeView view, Context context) {
        this.mHomeView = view;
        this.mHomeModel = new HomeModelImpl();
        this.mContext = context;
    }

    public void getCityWeather(String city) {
        mHomeModel.getWeatherByCityName(city, new Callback<H5Weather>() {
            @Override
            public void onResponse(Call<H5Weather> call, Response<H5Weather> response) {
                Log.d("neworin", "获取成功: " + response.body());
            }

            @Override
            public void onFailure(Call<H5Weather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}