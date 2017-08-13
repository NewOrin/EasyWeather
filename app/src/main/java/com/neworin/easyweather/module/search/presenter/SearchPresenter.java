package com.neworin.easyweather.module.search.presenter;

import android.content.Context;

import com.neworin.easyweather.entity.H5Weather;
import com.neworin.easyweather.module.home.model.HomeModelImpl;
import com.neworin.easyweather.module.home.model.IHomeModel;
import com.neworin.easyweather.module.home.view.ISearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
public class SearchPresenter {

    private ISearchView mHomeView;
    private IHomeModel mHomeModel;
    private Context mContext;

    public SearchPresenter(ISearchView view, Context context) {
        this.mHomeView = view;
        this.mHomeModel = new HomeModelImpl();
        this.mContext = context;
    }

    public void getCityWeather(String city) {
        mHomeModel.getWeatherByCityName(city, new Callback<H5Weather>() {
            @Override
            public void onResponse(Call<H5Weather> call, Response<H5Weather> response) {
                mHomeView.refreshData(response.body().HeWeather5);
            }

            @Override
            public void onFailure(Call<H5Weather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void searchCity(String city) {
        mHomeModel.searchCity(city, new Callback<H5Weather>() {
            @Override
            public void onResponse(Call<H5Weather> call, Response<H5Weather> response) {

            }

            @Override
            public void onFailure(Call<H5Weather> call, Throwable t) {

            }
        });
    }
}