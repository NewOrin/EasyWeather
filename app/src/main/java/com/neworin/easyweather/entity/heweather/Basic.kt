package com.neworin.easyweather.entity.heweather

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class Basic : BaseEntity() {

    var city: String? = null
    var cnty: String? = null
    var id: String? = null
    var lat: String? = null
    var lon: String? = null
    var prov: String? = null
    var update: UpdateBean? = null
    var status : String? = null

    inner class UpdateBean : BaseEntity() {

        var loc: String? = null
        var utc: String? = null

    }
}
