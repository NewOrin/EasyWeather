package com.neworin.easyweather.module.home.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.neworin.easyweather.entity.heweather.Basic;
import com.neworin.easyweather.entity.heweather.H5Weather;
import com.neworin.easyweather.module.home.model.HomeModelImpl;
import com.neworin.easyweather.module.home.model.IHomeModel;
import com.neworin.easyweather.module.home.view.IHomeView;
import com.neworin.easyweather.utils.Constant;
import com.neworin.easyweather.utils.SharedPreferenceUtil;

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
                mHomeView.refreshData(response.body().HeWeather5);
            }

            @Override
            public void onFailure(Call<H5Weather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 获取从搜索页面传递的数据
     *
     * @param intent
     * @return
     */
    public Basic getCityId(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            return (Basic) bundle.getSerializable(Constant.INSTANCE.getINTENT_KEY_STR_01());
        }
        return null;
    }

    public Basic getSharedPrefCity() {
        String basicStr = SharedPreferenceUtil.getString(mContext, Constant.INSTANCE.getSHARED_CITIES_TAG());
        if (null != basicStr) {
            List<Basic> basicList = JSON.parseArray(basicStr, Basic.class);
            return basicList.get(0);
        }
        return null;
    }
}