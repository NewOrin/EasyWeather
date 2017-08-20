package com.neworin.easyweather.entity.heweather

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class Now : BaseEntity() {

    var cond: CondBean? = null
    var fl: String? = null
    var hum: String? = null
    var pcpn: String? = null
    var pres: String? = null
    var tmp: String? = null
    var vis: String? = null
    var wind: WindBean? = null

    inner class CondBean : BaseEntity() {

        var code: String? = null
        var txt: String? = null

    }

    inner class WindBean : BaseEntity() {

        var deg: String? = null
        var dir: String? = null
        var sc: String? = null
        var spd: String? = null

    }
}
