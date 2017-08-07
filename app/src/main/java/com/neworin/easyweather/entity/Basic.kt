package com.neworin.easyweather.entity

/**
 * Author: NewOrin Zhang
 * Email: neworin@163.com
 * Description:
 */
class Basic : BaseEntity() {

    private val city: String? = null
    private val cnty: String? = null
    private val id: String? = null
    private val lat: String? = null
    private val lon: String? = null
    private val update: UpdateBean? = null

    internal inner class UpdateBean {

        private val loc: String? = null
        private val utc: String? = null

    }
}
