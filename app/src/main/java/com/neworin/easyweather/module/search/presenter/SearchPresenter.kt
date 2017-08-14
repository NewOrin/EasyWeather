package com.neworin.easyweather.module.search.presenter

import android.content.Context
import android.content.SharedPreferences
import com.alibaba.fastjson.JSON
import com.neworin.easyweather.entity.Basic

import com.neworin.easyweather.entity.H5Weather
import com.neworin.easyweather.module.home.model.HomeModelImpl
import com.neworin.easyweather.module.home.model.IHomeModel
import com.neworin.easyweather.module.home.view.ISearchView
import com.neworin.easyweather.module.search.model.SearchModel
import com.neworin.easyweather.module.search.model.SearchModelImpl
import com.neworin.easyweather.utils.Constant
import com.neworin.easyweather.utils.SharedPreferenceUtil

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class SearchPresenter(private val mSearchView: ISearchView, private val mContext: Context) {

    private val mSearchModel: SearchModel

    init {
        this.mSearchModel = SearchModelImpl()
    }

    fun searchCity(city: String) {
        mSearchModel.searchCity(city, object : Callback<H5Weather> {
            override fun onResponse(call: Call<H5Weather>, response: Response<H5Weather>) {
                mSearchView.refreshData(response.body().HeWeather5)
            }

            override fun onFailure(call: Call<H5Weather>, t: Throwable) {

            }
        })
    }

    fun insertSharedCity(basic: Basic) {
        val cities = SharedPreferenceUtil.getString(mContext, Constant.SHARED_CITIES_TAG)
        var basicList = JSON.parseArray(cities, Basic::class.java)
        if (null == basicList) {
            basicList = ArrayList()
        }
        basicList.add(0, basic)
        SharedPreferenceUtil.putString(mContext, Constant.SHARED_CITIES_TAG, JSON.toJSONString(basicList))
    }
}