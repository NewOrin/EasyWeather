package com.neworin.easyweather.module.search.model

import com.neworin.easyweather.entity.heweather.H5Weather
import com.neworin.easyweather.http.ServiceGenerator

import retrofit2.Callback

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class SearchModelImpl : SearchModel {

    override fun searchCity(city: String, callback: Callback<H5Weather>) {
        val call = ServiceGenerator.createService().searchCity(city)
        call.enqueue(callback)
    }
}