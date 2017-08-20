package com.neworin.easyweather.module.home.view

import com.neworin.easyweather.entity.Weather
import com.neworin.easyweather.entity.db.Citys

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface ISearchView {

    fun refreshData(cityList:List<Citys>)

}