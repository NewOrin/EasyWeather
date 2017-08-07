package com.neworin.easyweather.home.view

import com.neworin.easyweather.entity.Weather

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface IHomeView {
    fun refreshData(list: List<Weather>)
}