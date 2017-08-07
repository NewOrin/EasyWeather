package com.neworin.easyweather.entity

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class Aqi {

    var city: City? = null

    inner class City {
        var aqi: String? = null
        var pm10: String? = null
        var pm25: String? = null
        var qlty: String? = null
    }
}
