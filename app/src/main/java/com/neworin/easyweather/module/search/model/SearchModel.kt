package com.neworin.easyweather.module.search.model

import com.neworin.easyweather.entity.H5Weather

import retrofit2.Callback

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface SearchModel {

    fun searchCity(city: String, callback: Callback<H5Weather>)
}