package com.neworin.easyweather.module.home.view

import com.neworin.easyweather.entity.heweather.Weather

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
interface IHomeView {
    fun refreshData(list: List<Weather>)
}