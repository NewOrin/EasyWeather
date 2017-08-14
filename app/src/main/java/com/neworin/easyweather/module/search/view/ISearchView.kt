package com.neworin.easyweather.module.home.view

import com.neworin.easyweather.entity.Weather

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface ISearchView {

    fun refreshData(weatherList:List<Weather>)

}